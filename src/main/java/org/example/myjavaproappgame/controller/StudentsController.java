package org.example.myjavaproappgame.controller;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateRequestDto;
import org.example.myjavaproappgame.dto.studentDto.StudentCreateResponseDto;
import org.example.myjavaproappgame.dto.studentDto.StudentResponseDto;
import org.example.myjavaproappgame.service.StudentServiсe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentsController {

    private final StudentServiсe service;

    @PostMapping
    public ResponseEntity<StudentCreateResponseDto> createStudent(@RequestBody StudentCreateRequestDto request){
        return new ResponseEntity<>(service.createStudent(request), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


    @GetMapping("/findByLevel/{level}")
    public ResponseEntity<List<StudentResponseDto>> findByLevel(@PathVariable String level){
        return new ResponseEntity<>(service.findByLevel(level), HttpStatus.OK);
    }
}