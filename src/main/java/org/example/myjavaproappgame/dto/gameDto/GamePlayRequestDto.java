package org.example.myjavaproappgame.dto.gameDto;

import org.example.myjavaproappgame.dto.cardDto.CardResponseDto;

import java.util.List;

public class GamePlayRequestDto {

    private Long gameId;
    private List<CardResponseDto> userAnswers;  // список ответов пользователя

}
