package org.example.myjavaproappgame.dto.gameDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myjavaproappgame.dto.cardDto.CardResponseGameDto;
import org.example.myjavaproappgame.entity.Card;
import org.example.myjavaproappgame.entity.ResultStatus;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateResponseDto {

    private Long id;
    private Integer numbersOfCards;
    private Integer numbersOfRightAnswer = 0;
    private ResultStatus status;
    private String level;

}
