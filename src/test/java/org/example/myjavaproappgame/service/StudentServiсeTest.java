package org.example.myjavaproappgame.service;

import org.example.myjavaproappgame.dto.studentDto.StudentCreateRequestDto;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateResponseDto;
import org.example.myjavaproappgame.dto.studentDto.StudentResponseDto;
import org.example.myjavaproappgame.entity.Student;
import org.example.myjavaproappgame.repository.StudentRepository;
import org.example.myjavaproappgame.service.exception.AlreadyExistException;
import org.example.myjavaproappgame.service.exception.NotFoundException;
import org.example.myjavaproappgame.service.exception.ValidationException;
import org.example.myjavaproappgame.service.util.StudentConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class StudentServiсeTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    @InjectMocks
    private StudentService studentService;

    private StudentCreateResponseDto createResponseDto;
    private StudentCreateRequestDto request;
    private StudentResponseDto responseDto;
    private StudentResponseDto responseDto2;
    private StudentResponseDto responseDto3;

    Student student;
    Student student2;
    Student student3;

    @BeforeEach
    void setUp() {
        createResponseDto = new StudentCreateResponseDto();
        createResponseDto.setEmail("user@uni.com");

        request = new StudentCreateRequestDto();
        request.setEmail("user@uni.com");

        responseDto = new StudentResponseDto();
        responseDto.setEmail("user@uni.com");
        responseDto.setLevel("B2");

        responseDto2 = new StudentResponseDto();
        responseDto2.setEmail("user2@uni.com");
        responseDto2.setLevel("A2");

        responseDto3 = new StudentResponseDto();
        responseDto3.setEmail("user3@uni.com");
        responseDto3.setLevel("B1");

        student = new Student();
        student.setEmail("user@uni.com");
        student.setLevel("B2");

        student2 = new Student();
        student2.setEmail("user2@uni.com");
        student2.setLevel("A2");

        student3 = new Student();
        student3.setEmail("user3@uni.com");
        student3.setLevel("B1");
    }

    @Test
    void createStudent_success() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(converter.fromDto(request)).thenReturn(student);
        when(repository.save(student)).thenReturn(student);
        when(converter.toCreateDto(student)).thenReturn(createResponseDto);

        StudentCreateResponseDto result = studentService.createStudent(request);

        assertNotNull(result);
        assertEquals(createResponseDto.getEmail(), result.getEmail());
        verify(repository, times(1)).findByEmail(anyString());
        verify(repository, times(1)).save(student);
    }

    @Test
    void createStudent_alreadyExists_throwsException() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(student));

        assertThrows(AlreadyExistException.class, () -> studentService.createStudent(request));

        verify(repository, times(1)).findByEmail(anyString());
        verify(repository, never()).save(any(Student.class));
    }

    @Test
    void findAll_success() {

        when(repository.findAll()).thenReturn(Arrays.asList(student, student2, student3));

        when(converter.toDto(student)).thenReturn(responseDto);
        when(converter.toDto(student2)).thenReturn(responseDto2);
        when(converter.toDto(student3)).thenReturn(responseDto3);


        List<StudentResponseDto> result = studentService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByEmail_success() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(student));
        when(converter.toDto(student)).thenReturn(responseDto);

        StudentResponseDto result = studentService.findByEmail(student.getEmail());

        assertNotNull(result);
        assertEquals(responseDto.getEmail(), result.getEmail());
        verify(repository, times(1)).findByEmail(anyString());
    }

    @Test
    void findByEmail_notFound_throwsException() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> studentService.findByEmail("user@uni.com"));

        verify(repository, times(1)).findByEmail(anyString());
    }

    @Test
    void findByLevel_success() {
        when(repository.findByLevel(anyString())).thenReturn(Arrays.asList(student, student3));
        when(converter.toDto(student)).thenReturn(responseDto);
        when(converter.toDto(student3)).thenReturn(responseDto3);

        List<StudentResponseDto> result = studentService.findByLevel("B2");

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(repository, times(1)).findByLevel(anyString());
    }

    @Test
    void findByLevel_notFound_throwsException() {
        when(repository.findByLevel(anyString())).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> studentService.findByLevel("A1"));

        verify(repository, times(1)).findByLevel(anyString());
    }
}