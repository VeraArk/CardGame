package org.example.myjavaproappgame.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateRequestDto;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateResponseDto;
import org.example.myjavaproappgame.dto.studentDto.StudentResponseDto;
import org.example.myjavaproappgame.entity.Student;
import org.example.myjavaproappgame.repository.StudentRepository;
import org.example.myjavaproappgame.service.exception.AlreadyExistException;
import org.example.myjavaproappgame.service.exception.NotFoundException;
import org.example.myjavaproappgame.service.util.StudentConverter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentConverter converter;


    @Transactional
    public StudentCreateResponseDto createStudent(StudentCreateRequestDto request) {

        if (repository.findByEmail(request.getEmail()).isEmpty()) {
            Student newStudent = converter.fromDto(request);

            Student savedStudent = repository.save(newStudent);

            return converter.toCreateDto(savedStudent);

        } else {
            throw new AlreadyExistException("This student " + request.getName() + " is already exist");
        }
    }

    public List<StudentResponseDto> findAll() {
        List<StudentResponseDto> list = repository.findAll().stream()
                .map(converter::toDto)
                .toList();
        return list;
    }

    public StudentResponseDto findByEmail(String email) {

        Optional<Student> studentOptional = repository.findByEmail(email);
        if (studentOptional.isPresent()) {
            StudentResponseDto responseDto = converter.toDto(studentOptional.get());
            return responseDto;
        } else {
            throw new NotFoundException("Manager with email " + email + " not found");
        }
    }

    public List<StudentResponseDto> findByLevel(String level) {
        List<StudentResponseDto> students = repository.findByLevel(level).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
        if (!students.isEmpty()) {
            return students;
        } else {
            throw new NotFoundException("No students found by the level: " + level);
        }
    }

}



