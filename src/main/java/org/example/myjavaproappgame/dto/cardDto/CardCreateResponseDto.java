package org.example.myjavaproappgame.dto.cardDto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardCreateResponseDto {

    private Long id;
    private String question;
    private String answer;
    private String topic;


}
