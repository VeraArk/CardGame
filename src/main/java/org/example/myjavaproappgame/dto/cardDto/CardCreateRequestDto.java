package org.example.myjavaproappgame.dto.cardDto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardCreateRequestDto {

    private String question;
    private String answer;
    private String level;
    private String topic;

}
