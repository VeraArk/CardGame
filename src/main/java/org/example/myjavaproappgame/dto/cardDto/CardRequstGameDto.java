package org.example.myjavaproappgame.dto.cardDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequstGameDto {
    private Long id;
    private String answer;
}
