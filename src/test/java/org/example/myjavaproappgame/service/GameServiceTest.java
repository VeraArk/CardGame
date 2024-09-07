package org.example.myjavaproappgame.service;

import org.example.myjavaproappgame.dto.cardDto.CardResponseGameDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateResponseDto;
import org.example.myjavaproappgame.dto.gameDto.GamePlayRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameResponseDto;
import org.example.myjavaproappgame.entity.Card;
import org.example.myjavaproappgame.entity.Game;
import org.example.myjavaproappgame.entity.ResultStatus;
import org.example.myjavaproappgame.entity.Student;
import org.example.myjavaproappgame.repository.CardRepository;
import org.example.myjavaproappgame.repository.GameRepository;
import org.example.myjavaproappgame.repository.StudentRepository;
import org.example.myjavaproappgame.service.exception.NotFoundException;
import org.example.myjavaproappgame.service.util.GameConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameConverter gameConverter;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CardService cardService;

    @InjectMocks
    GameService gameService;


    private GameCreateRequestDto requestDto;
    private GamePlayRequestDto gamePlayRequestDto;
    private GameResponseDto gameResponseDto;

    private CardResponseGameDto userAnswer;
    private Student student;
    private Card card;
    private Card card2;
    private Game game;


    @BeforeEach
    void setUp() {
        requestDto = new GameCreateRequestDto();
        requestDto.setNumbersOfCards(2);
        requestDto.setTopic("animal");
        requestDto.setLevel("A2");

        gamePlayRequestDto = new GamePlayRequestDto();
        gamePlayRequestDto.setGameId(1L);

        student = new Student();
        student.setId(1L);
        student.setName("John");

        card = new Card();
        card.setId(6L);
        card.setAnswer("собака");

        card2 = new Card();
        card2.setId(7L);
        card2.setAnswer("кот");

        game = new Game();
        game.setId(1L);
        game.setStudent(student);
        game.setNumbersOfCards(5);
        game.setNumbersOfRightAnswer(5);
        game.setLevel("A2");
        game.setStatus(ResultStatus.IN_PROGRESS);

    }


    @Test
    void testStartNewGame() {
        // находім студента
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // вызываем с помощью мок cardService для возвращения списка карточек
        when(cardService.findByLevelAndTopic(requestDto.getNumbersOfCards(), requestDto.getLevel(), requestDto.getTopic()))
                .thenReturn(List.of(card));

        // сохранение игру и возвращаем тот же объект game
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        //Конвертіруем игру в DTO
        when(gameConverter.toCreateDto(any(Game.class))).thenReturn(new GameCreateResponseDto());

        // Вызываем тестируемый метод
        GameCreateResponseDto response = gameService.startNewGame(1L, requestDto);

        // Проверяем, что результат не null
        assertNotNull(response);

        // Проверяем, что игра была сохранена один раз
        verify(gameRepository, times(1)).save(any(Game.class));

        // Дополнительно: проверяем, что был вызван метод конвертера
        verify(gameConverter, times(1)).toCreateDto(any(Game.class));
    }

    @Test
    public void testAnswerQuestion_correctAnswers() {
        userAnswer = new CardResponseGameDto();
        userAnswer.setId(6L);
        userAnswer.setAnswer("собака");

        gamePlayRequestDto.setUserAnswers(List.of(userAnswer));

        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
        when(cardRepository.findById(6L)).thenReturn(Optional.of(card));

        gameService.answerQuestion(gamePlayRequestDto);

        // Проверяем, что количество правильных ответов увеличилось.
        // изначально в сетап установлено пять правильных, плюс еще один
        assertEquals(6, game.getNumbersOfRightAnswer());

        // Проверяем, что игра была сохранена
        verify(gameRepository).save(game);
    }

    @Test
    public void testAnswerQuestion_incorrectAnswer() {

        CardResponseGameDto userAnswer = new CardResponseGameDto();
        userAnswer.setId(card2.getId());
        userAnswer.setAnswer("wrong_answer");
        gamePlayRequestDto.setUserAnswers(List.of(userAnswer));

        game.setNumbersOfRightAnswer(0);

        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
        when(cardRepository.findById(card2.getId())).thenReturn(Optional.of(card));

        gameService.answerQuestion(gamePlayRequestDto);

        assertEquals(0, game.getNumbersOfRightAnswer());

        verify(gameRepository).save(game);
    }

    @Test
    public void testAnswerQuestion_gameNotFound() {

        gamePlayRequestDto.setGameId(game.getId());

        when(gameRepository.findById(game.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> gameService.answerQuestion(gamePlayRequestDto));
    }

    @Test
    public void testFinishGame_statusExcellent() {

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        gameResponseDto = new GameResponseDto();
        when(gameConverter.toDto(game)).thenReturn(gameResponseDto);

        GameResponseDto result = gameService.finishGame(1L);

        assertEquals(ResultStatus.EXCELLENT, game.getStatus());

        verify(gameRepository).save(game);
        verify(gameConverter).toDto(game);
    }

    @Test
    public void testFinishGame_statusGood() {
        Game game = new Game();
        game.setId(1L);
        game.setNumbersOfRightAnswer(4);
        game.setNumbersOfCards(5);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        GameResponseDto responseDto = new GameResponseDto();
        when(gameConverter.toDto(game)).thenReturn(responseDto);

        GameResponseDto result = gameService.finishGame(1L);

        assertEquals(ResultStatus.GOOD, game.getStatus());
        verify(gameRepository).save(game);
        verify(gameConverter).toDto(game);
    }

    @Test
    public void testFinishGame_statusSatisfactory() {
        // Подготовка данных
        Game game = new Game();
        game.setId(1L);
        game.setNumbersOfRightAnswer(3);
        game.setNumbersOfCards(5);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        GameResponseDto responseDto = new GameResponseDto();
        when(gameConverter.toDto(game)).thenReturn(responseDto);

        // Вызов метода
        GameResponseDto result = gameService.finishGame(1L);

        // Проверка
        assertEquals(ResultStatus.SATISFACTORY, game.getStatus());
        verify(gameRepository).save(game);
        verify(gameConverter).toDto(game);
    }

    @Test
    public void testFinishGame_statusPoor() {
        // Подготовка данных
        Game game = new Game();
        game.setId(1L);
        game.setNumbersOfRightAnswer(2);
        game.setNumbersOfCards(5);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        GameResponseDto responseDto = new GameResponseDto();
        when(gameConverter.toDto(game)).thenReturn(responseDto);

        // Вызов метода
        GameResponseDto result = gameService.finishGame(1L);

        // Проверка
        assertEquals(ResultStatus.POOR, game.getStatus());
        verify(gameRepository).save(game);
        verify(gameConverter).toDto(game);
    }

    @Test
    public void testFinishGame_gameNotFound() {

        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> gameService.finishGame(1L));

        verify(gameRepository, never()).save(any());
        verify(gameConverter, never()).toDto(any());
    }
}


