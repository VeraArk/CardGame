package org.example.myjavaproappgame.dto.gameDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myjavaproappgame.entity.Result;
import org.example.myjavaproappgame.entity.Student;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateResponseDto {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String studentName;
    private String resultStatus;
}
