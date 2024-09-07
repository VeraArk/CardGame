package org.example.myjavaproappgame.dto.studentDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateResponseDto {

    private Long id;
    private String email;
    private String name;
    private String level;
}
