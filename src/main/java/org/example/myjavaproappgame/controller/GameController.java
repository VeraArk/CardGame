package org.example.myjavaproappgame.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.gameDto.GameCreateRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateResponseDto;
import org.example.myjavaproappgame.dto.gameDto.GamePlayRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameResponseDto;
import org.example.myjavaproappgame.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<GameCreateResponseDto> startNewGame(@Valid @RequestParam Long studentId,
                                                             @Valid @RequestBody GameCreateRequestDto requestDto) {
        GameCreateResponseDto response = gameService.startNewGame(studentId, requestDto);
        return ResponseEntity.ok(response);
    }

    // во время игры пользователь взаимодействует с интерфейсом. Когда все ответы собраны в лист GamePlayRequestDto,
    // вызывается метод контроллера

//    // отправляет лист ответов студента в метод answerQuestion()
//    //toDO нужна ли валидация ответов студента?!
//    @PostMapping("/answer")
//    public ResponseEntity<Void> answerQuestion(@RequestBody GamePlayRequestDto requestDto) {
//        gameService.answerQuestion(requestDto);
//        return ResponseEntity.ok().build();
//    }

    // возвращает результат игры - статус
    @GetMapping("/finish")
    public ResponseEntity<GameResponseDto> finishGame(@RequestParam Long gameId) {
        GameResponseDto gameResponseDto = gameService.finishGame(gameId);
        return ResponseEntity.ok(gameResponseDto);
    }
}

