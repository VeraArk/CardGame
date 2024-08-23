package org.example.myjavaproappgame.dto.studentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequestDto {

    private String name;
    private String email;
    private String password;
}
