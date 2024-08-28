package org.example.myjavaproappgame.service.util;

import org.example.myjavaproappgame.dto.cardDto.CardCreateRequestDto;
import org.example.myjavaproappgame.dto.cardDto.CardCreateResponseDto;
import org.example.myjavaproappgame.dto.cardDto.CardResponseDto;
import org.example.myjavaproappgame.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {
    public Card fromDto(CardCreateRequestDto dto){
        Card card = new Card();
        card.setQuestion(dto.getQuestion());
        card.setAnswer(dto.getAnswer());
        card.setLevel(dto.getLevel());
        card.setTopic(dto.getTopic());
        return card;
    }

    public CardCreateResponseDto toCreateDto (Card card){
        return new CardCreateResponseDto(card.getId(), card.getQuestion(), card.getAnswer(), card.getLevel(), card.getTopic());
    }

    public CardResponseDto toDto (Card card){
        return new CardResponseDto(card.getId(), card.getAnswer());
    }

}
