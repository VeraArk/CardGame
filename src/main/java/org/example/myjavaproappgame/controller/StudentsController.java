package org.example.myjavaproappgame.controller;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateRequestDto;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateResponseDto;
import org.example.myjavaproappgame.dto.studentDto.StudentResponseDto;
import org.example.myjavaproappgame.service.StudentServiсe;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentsController {

    private final StudentServiсe service;

    @PostMapping
    public StudentCreateResponseDto createStudent(@RequestBody StudentCreateRequestDto request){
        return service.createStudent(request);
    }


    @GetMapping
    public List<StudentResponseDto> findAll(){
        return service.findAll();
    }


    @GetMapping("/findByLevel/{level}")
    public List<StudentResponseDto> findByLevel(@PathVariable String level){
        return service.findByLevel(level);
    }
}