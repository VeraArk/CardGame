package org.example.myjavaproappgame.service.util;


import org.example.myjavaproappgame.dto.gameDto.GameCreateRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateResponseDto;

import org.example.myjavaproappgame.dto.gameDto.GameResponseDto;
import org.example.myjavaproappgame.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameConverter {

    public Game fromCreateRequst(GameCreateRequestDto dto) {
        Game game = new Game();
        game.setNumbersOfCards(dto.getNumbersOfCards());
        return game;
    }

    public GameCreateResponseDto toCreateDto(Game game) {
        GameCreateResponseDto dto = new GameCreateResponseDto();
        dto.setId(game.getId());
        dto.setNumbersOfCards(game.getNumbersOfCards());
        dto.setNumbersOfRightAnswer(game.getNumbersOfRightAnswer());
        dto.setStatus(game.getStatus());

        return dto;

    }

    public GameResponseDto toDto(Game game) {
        GameResponseDto dto = new GameResponseDto();
        dto.setNumbersOfCards(game.getNumbersOfCards());
        dto.setNumbersOfRightAnswer(game.getNumbersOfRightAnswer());
        dto.setStatus(game.getStatus());
        return dto;

    }
}



