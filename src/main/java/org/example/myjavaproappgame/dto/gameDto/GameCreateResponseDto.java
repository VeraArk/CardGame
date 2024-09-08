package org.example.myjavaproappgame.dto.gameDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myjavaproappgame.entity.ResultStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateResponseDto {

    private Long id;
    private Integer numberOfCards;
    private Integer numberOfRightAnswer = 0;
    private ResultStatus status;
    private String level;
    private String topic;

}
