package org.example.myjavaproappgame.controller;

import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.cardDto.CardCreateRequestDto;
import org.example.myjavaproappgame.dto.cardDto.CardCreateResponseDto;
import org.example.myjavaproappgame.dto.cardDto.CardResponseDto;
import org.example.myjavaproappgame.service.CardServiсe;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardsController {
    private final CardServiсe service;

    @PostMapping
    public CardCreateResponseDto createNewCard(@RequestBody CardCreateRequestDto request) {
        return service.createCard(request);
    }


    @GetMapping
    public List<CardResponseDto> findAll() {
        return service.findAll();
    }


    @GetMapping("/findByTopic/{topic}")
    public List<CardResponseDto> findByTopic(@PathVariable String topic) {
        return service.findByTopic(topic);
    }

}
