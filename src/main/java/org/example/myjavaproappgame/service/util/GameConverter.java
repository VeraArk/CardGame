package org.example.myjavaproappgame.service.util;

import org.example.myjavaproappgame.dto.gameDto.GameCreateResponseDto;
import org.example.myjavaproappgame.dto.gameDto.GameResponseDto;
import org.example.myjavaproappgame.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameConverter {

    public GameCreateResponseDto toCreateDto(Game game) {
        GameCreateResponseDto dto = new GameCreateResponseDto();
        dto.setId(game.getId());
        dto.setNumberOfCards(game.getNumberOfCards());
        dto.setNumberOfRightAnswer(game.getNumberOfRightAnswer());
        dto.setStatus(game.getStatus());
        dto.setLevel(game.getLevel());

        return dto;
    }

    public GameResponseDto toDto(Game game) {
        GameResponseDto dto = new GameResponseDto();
        dto.setNumberOfRightAnswer(game.getNumberOfRightAnswer());
        dto.setStatus(game.getStatus());
        return dto;

    }
}



