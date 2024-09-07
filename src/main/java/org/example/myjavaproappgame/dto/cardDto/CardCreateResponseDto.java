package org.example.myjavaproappgame.dto.cardDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateResponseDto {

    private Long id;
    @NotBlank
    @Size(min =3, max =15, message = "Question length must be between from 3 to 15 character")
    private String question;

    @NotBlank
    @Size(min =3, max =15, message = "Answer length must be between from 3 to 15 character")
    private String answer;
    private String level;
    private String topic;
}
