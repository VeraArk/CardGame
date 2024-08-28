package org.example.myjavaproappgame.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.cardDto.CardCreateRequestDto;
import org.example.myjavaproappgame.dto.cardDto.CardCreateResponseDto;
import org.example.myjavaproappgame.dto.cardDto.CardResponseDto;
import org.example.myjavaproappgame.service.CardServiсe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardsController {
    private final CardServiсe service;

    @PostMapping
    public ResponseEntity<CardCreateResponseDto> createNewCard(@Valid @RequestBody CardCreateRequestDto request) {
        return ResponseEntity.ok(service.createCard(request));
    }

    @GetMapping
    public ResponseEntity<List<CardResponseDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


    @GetMapping("/findByLevel/{level}")
    public ResponseEntity<List<CardResponseDto>> findByLevel(@PathVariable String level) {
        return new ResponseEntity<>(service.findByLevel(level), HttpStatus.OK);
    }

    @GetMapping("/findByTopic/{topic}")
    public ResponseEntity<List<CardResponseDto>> findByTopic(@PathVariable String topic) {
        return new ResponseEntity<>(service.findByTopic(topic), HttpStatus.OK);
    }

}
