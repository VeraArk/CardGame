package org.example.myjavaproappgame.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.cardDto.CardRequstGameDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateResponseDto;
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


    @PostMapping("/answer")
    public ResponseEntity<Void> answerQuestion(@Valid @RequestParam Long gameId, @RequestBody CardRequstGameDto cardRequstGameDto) {
        gameService.answerQuestion(gameId, cardRequstGameDto);
        return ResponseEntity.ok().build();
    }

    // возвращает результат игры - статус
    @GetMapping("/finish")
    public ResponseEntity<GameResponseDto> finishGame(@RequestParam Long gameId) {
        GameResponseDto gameResponseDto = gameService.finishGame(gameId);
        return ResponseEntity.ok(gameResponseDto);
    }
}

