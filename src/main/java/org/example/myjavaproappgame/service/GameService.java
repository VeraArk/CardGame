package org.example.myjavaproappgame.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.cardDto.CardRequstGameDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateResponseDto;
import org.example.myjavaproappgame.dto.gameDto.GameResponseDto;
import org.example.myjavaproappgame.entity.Card;
import org.example.myjavaproappgame.entity.Game;
import org.example.myjavaproappgame.entity.ResultStatus;
import org.example.myjavaproappgame.entity.Student;
import org.example.myjavaproappgame.repository.CardRepository;
import org.example.myjavaproappgame.repository.GameRepository;
import org.example.myjavaproappgame.repository.StudentRepository;
import org.example.myjavaproappgame.service.exception.NotFoundException;
import org.example.myjavaproappgame.service.util.CardConverter;
import org.example.myjavaproappgame.service.util.GameConverter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
@RequiredArgsConstructor
public class GameService {

    private final GameConverter gameConverter;
    private final GameRepository gameRepository;
    private final CardRepository cardRepository;
    private final StudentRepository studentRepository;
    private final CardConverter CardConverter;
    private final CardService cardService;

    @Transactional
    public GameCreateResponseDto startNewGame(Long studentId, GameCreateRequestDto requestDto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<Card> selectedCards = cardService.findByLevelAndTopic(requestDto.getNumberOfCards(), requestDto.getLevel(), requestDto.getTopic());

        Game game = new Game();
        game.setStudent(student);
        game.setNumberOfCards(requestDto.getNumberOfCards());
        game.setLevel(requestDto.getLevel());
        game.setStatus(ResultStatus.IN_PROGRESS);
        game.setCards(selectedCards);

        // Сохраняем игру в БД
        game = gameRepository.save(game);

        // Возвращаем DTO для ответа
        return gameConverter.toCreateDto(game);
    }

    // отвечаем по одной карточке
    @Transactional
    public boolean answerQuestion(Long gameID, CardRequstGameDto cardRequstGameDto) {
        Game game = gameRepository.findById(gameID)
                .orElseThrow(() -> new NotFoundException("Game is not found"));

        Card card = cardRepository.findById(cardRequstGameDto.getId())
                .orElseThrow(() -> new NotFoundException("Card not found"));
        boolean isCorrect = card.getAnswer().equalsIgnoreCase(cardRequstGameDto.getAnswer());

        if (isCorrect) {
            game.setNumberOfRightAnswer(game.getNumberOfRightAnswer() + 1);
        }

        gameRepository.save(game);

        return isCorrect;
    }

    @Transactional
    public GameResponseDto finishGame(Long gameId) {
        Game currentGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundException("Game not found"));

        int correctAnswers = currentGame.getNumberOfRightAnswer();
        int totalQuestions = currentGame.getNumberOfCards();

        if (correctAnswers == totalQuestions) {
            currentGame.setStatus(ResultStatus.EXCELLENT);
        } else if (correctAnswers >= totalQuestions * 0.75) {
            currentGame.setStatus(ResultStatus.GOOD);
        } else if (correctAnswers >= totalQuestions * 0.5) {
            currentGame.setStatus(ResultStatus.SATISFACTORY);
        } else {
            currentGame.setStatus(ResultStatus.POOR);
        }

        gameRepository.save(currentGame);

        // Возвращаем только необходимую информацию (статус-результат и число правильных ответов)

        return gameConverter.toDto(currentGame);
    }
}







