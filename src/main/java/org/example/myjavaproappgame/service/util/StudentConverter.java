package org.example.myjavaproappgame.service.util;

import org.example.myjavaproappgame.dto.studentDto.StudentCreateRequestDto;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateResponseDto;
import org.example.myjavaproappgame.dto.studentDto.StudentResponseDto;
import org.example.myjavaproappgame.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    public Student fromDto(StudentCreateRequestDto dto){
        Student student = new Student();
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        return student;
    }

    public StudentCreateResponseDto toCreateDto (Student student){
       return new StudentCreateResponseDto(student.getId(), student.getEmail(), student.getName(), student.getLevel());
    }

    public StudentResponseDto toDto (Student student){
        return new StudentResponseDto(student.getName(), student.getEmail(), student.getLevel());
    }
}

