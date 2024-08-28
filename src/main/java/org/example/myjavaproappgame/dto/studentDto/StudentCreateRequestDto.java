package org.example.myjavaproappgame.dto.studentDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequestDto {

    @NotBlank
    @Size(min =3, max =15, message = "Student name length must be between from 3 to 15 character")
    private String name;

    @NotBlank(message = "Email must be not blank")
    @Email
    private String email;

    @NotBlank (message = "Password must be not blank")
    @Size (min =6, message = "Password length must be from 6 character")
    private String password;

}
