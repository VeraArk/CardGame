package org.example.myjavaproappgame.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.myjavaproappgame.dto.cardDto.CardCreateRequestDto;
import org.example.myjavaproappgame.dto.cardDto.CardCreateResponseDto;
import org.example.myjavaproappgame.dto.cardDto.CardResponseDto;
import org.example.myjavaproappgame.entity.Card;
import org.example.myjavaproappgame.repository.CardRepository;
import org.example.myjavaproappgame.service.exception.AlreadyExistException;
import org.example.myjavaproappgame.service.exception.NotFoundException;
import org.example.myjavaproappgame.service.util.CardConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CardServiсe {

    private final CardRepository repository;
    private final CardConverter converter;

    public CardCreateResponseDto createCard(CardCreateRequestDto request) {
        if (repository.findByQuestion(request.getQuestion()).isEmpty()) {
            Card newCard = converter.fromDto(request);

            Card savedCard = repository.save(newCard);

            return converter.toCreateDto(savedCard);

        } else {
            throw new AlreadyExistException("This card " + request.getQuestion() + " is already exist");
        }
    }


    public List<CardResponseDto> findAll() {
        List<CardResponseDto> list = repository.findAll().stream()
                .map(converter::toDto)
                .toList();
        return list;
    }


    public List<CardResponseDto> findByLevel(String level) {
        List<CardResponseDto> cards = repository.findByLevel(level).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
        if (!cards.isEmpty()) {
            return cards;
        } else {
            throw new NotFoundException("No cards found with the topic: " + level);
        }
    }
}

