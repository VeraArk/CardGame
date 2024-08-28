package org.example.myjavaproappgame.dto.gameDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myjavaproappgame.entity.ResultStatus;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateResponseDto {

    private Long id;
    private Integer numbersOfCards;
    private Integer numbersOfRightAnswer;
    private ResultStatus status;

}
