package org.example.myjavaproappgame.dto.gameDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myjavaproappgame.dto.cardDto.CardRequstGameDto;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePlayRequestDto {

    private Long gameId;
    private List<CardRequstGameDto> userAnswers; // список ответов пользователя

}
