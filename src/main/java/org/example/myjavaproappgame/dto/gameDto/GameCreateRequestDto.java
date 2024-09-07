package org.example.myjavaproappgame.dto.gameDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myjavaproappgame.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateRequestDto {

    @NotBlank(message = "Field \"Number of cards\" must be not blank")
    @Pattern(regexp = "\\d+", message = "Field \"Numbers of cards\" can contain only digital")
    @Size(min =1, max =2)
    private Integer numbersOfCards;

    @NotBlank (message = "Field \"level\" must be not blank")
    @Pattern(regexp = "^[ABC]+[1-2]", message = "Level could be A1, A2, B1, B2, C1 or C2")
    private String level;


    private String topic;

}
