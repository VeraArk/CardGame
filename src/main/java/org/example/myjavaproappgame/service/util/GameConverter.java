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
        game.setStudentName(dto.getStudentName());
        return game;
    }

    public GameCreateResponseDto toCreateDto(Game game) {
        GameCreateResponseDto dto = new GameCreateResponseDto();
        dto.setId(game.getId());
        dto.setStartTime(game.getStartTime());
        dto.setEndTime(game.getEndTime());
        dto.setStudentName(game.getStudentName());

        dto.setResultStatus(String.valueOf(game.getResult().getStatus()));

        return dto;

    }

    public GameResponseDto toDto(Game game) {
        GameResponseDto dto = new GameResponseDto();
        dto.setStudentName(game.getStudentName());
        dto.setResultStatus(String.valueOf(game.getResult().getStatus()));
        return dto;

    }
}



