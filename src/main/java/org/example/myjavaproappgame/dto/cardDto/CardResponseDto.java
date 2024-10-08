package org.example.myjavaproappgame.dto.cardDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {

    private Long id;
    private String question;
    private String answer;
    private String level;
    private String topic;

}
