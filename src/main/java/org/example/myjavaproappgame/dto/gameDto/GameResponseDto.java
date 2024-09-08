package org.example.myjavaproappgame.dto.gameDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myjavaproappgame.entity.ResultStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDto {

    private Integer numberOfRightAnswer;
    private ResultStatus status;
}
