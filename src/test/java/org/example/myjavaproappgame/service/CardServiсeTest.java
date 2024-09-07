package org.example.myjavaproappgame.service;

import org.example.myjavaproappgame.dto.cardDto.CardCreateRequestDto;
import org.example.myjavaproappgame.dto.cardDto.CardCreateResponseDto;
import org.example.myjavaproappgame.dto.cardDto.CardResponseDto;
import org.example.myjavaproappgame.entity.Card;
import org.example.myjavaproappgame.repository.CardRepository;
import org.example.myjavaproappgame.service.exception.AlreadyExistException;
import org.example.myjavaproappgame.service.exception.NotFoundException;
import org.example.myjavaproappgame.service.util.CardConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository repository;

    @Mock
    private CardConverter converter;

    @InjectMocks
    private CardService service;

    private CardCreateRequestDto cardCreateRequestDto;
    private CardCreateResponseDto cardCreateResponseDto;
    private CardResponseDto cardResponseDto;
    private Card card;


    @BeforeEach
    void setUp() {
        cardCreateRequestDto = new CardCreateRequestDto();
        cardCreateRequestDto.setQuestion("cat");

        cardCreateResponseDto = new CardCreateResponseDto();
        cardCreateResponseDto.setId(1L);

        cardResponseDto = new CardResponseDto();
        cardResponseDto.setId(1L);

        card = new Card();
        card.setId(1L);
        card.setTopic("PastPerfect");
        card.setLevel("A2");
    }

    @Test
    void createCard_success() {
        when(repository.findByQuestion(anyString())).thenReturn(Optional.empty());
        when(converter.fromDto(cardCreateRequestDto)).thenReturn(card);
        when(repository.save(card)).thenReturn(card);
        when(converter.toCreateDto(card)).thenReturn(cardCreateResponseDto);

        CardCreateResponseDto result = service.createCard(cardCreateRequestDto);

        assertNotNull(result);
        assertEquals(cardCreateResponseDto.getId(), result.getId());
        verify(repository, times(1)).findByQuestion(anyString());
        verify(repository, times(1)).save(card);
    }

    @Test
    void createCard_alreadyExists_throwsException() {
        when(repository.findByQuestion(anyString())).thenReturn(Optional.of(card));

        assertThrows(AlreadyExistException.class, () -> service.createCard(cardCreateRequestDto));

        verify(repository, times(1)).findByQuestion(anyString());
        verify(repository, never()).save(any(Card.class));
    }

    @Test
    void findAll_success() {
        when(repository.findAll()).thenReturn(Arrays.asList(card));
        when(converter.toDto(card)).thenReturn(cardResponseDto);

        List<CardResponseDto> result = service.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByLevel_success() {
        when(repository.findByLevel(anyString())).thenReturn(Optional.of(card));
        when(converter.toDto(card)).thenReturn(cardResponseDto);

        List<CardResponseDto> result = service.findByLevel("A1");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repository, times(1)).findByLevel(anyString());
    }

    @Test
    void findByLevel_notFound_throwsException() {
        when(repository.findByLevel(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findByLevel("A1"));

        verify(repository, times(1)).findByLevel(anyString());
    }

    @Test
    void findByTopic_success() {
        when(repository.findByTopic(anyString())).thenReturn(Optional.of(card));
        ;
        when(converter.toDto(card)).thenReturn(cardResponseDto);

        List<CardResponseDto> result = service.findByTopic("animal");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repository, times(1)).findByTopic(anyString());
    }

    @Test
    void findByTopic_notFound_throwsException() {
        when(repository.findByTopic(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.findByTopic("animal"));

        verify(repository, times(1)).findByTopic(anyString());
    }

    @Test
    void findByLevelAndTopic_success() {
        when(repository.findAll()).thenReturn(Arrays.asList(card));

        List<Card> result = service.findByLevelAndTopic(1, "PastPerfect", "A2");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByLevelAndTopic_notFound_throwsException() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> service.findByLevelAndTopic(1, "PastPerfect", "A2"));

        verify(repository, times(1)).findAll();
    }
}