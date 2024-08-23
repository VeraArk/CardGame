package org.example.myjavaproappgame.dto.studentDto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentCreateResponseDto {

    private Long id;
    private String email;
    private String name;
    private String level;
}
