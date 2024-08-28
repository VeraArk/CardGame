package org.example.myjavaproappgame.dto.cardDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {
    private Long id;
    private String answer;

}
