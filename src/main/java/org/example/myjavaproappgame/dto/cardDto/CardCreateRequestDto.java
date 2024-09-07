package org.example.myjavaproappgame.dto.cardDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateRequestDto {

    @NotBlank (message = "Field \"question\" must be not blank")
    @Pattern(regexp = "[A-Za-zА-яа-я]+")
    @Size(min =2, max =25)
    private String question;

    @NotBlank (message = "Field \"answer\" must be not blank")
    @Pattern(regexp = "[А-яа-яA-Za-z]+")
    @Size (min =2, max =25)
    private String answer;

    @NotBlank (message = "Field \"level\" must be not blank")
    @Pattern(regexp = "^[ABC]+[1-2]", message = "Level could be A1, A2, B1, B2, C1 or C2")
    private String level;

    @NotBlank (message = "Field \"topic\" must be not blank")
    @Pattern(regexp = "[A-Za-z]+")
    private String topic;

}
