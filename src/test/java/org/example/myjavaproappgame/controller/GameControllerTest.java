package org.example.myjavaproappgame.controller;

import org.example.myjavaproappgame.dto.gameDto.GameCreateRequestDto;
import org.example.myjavaproappgame.dto.gameDto.GameCreateResponseDto;
import org.example.myjavaproappgame.dto.gameDto.GameResponseDto;
import org.example.myjavaproappgame.entity.ResultStatus;
import org.example.myjavaproappgame.service.CardService;
import org.example.myjavaproappgame.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @MockBean
    private GameService gameService;

    @BeforeEach
    void setUp(){
        GameCreateResponseDto mockResponse = new GameCreateResponseDto(1L, 1,0, ResultStatus.IN_PROGRESS,"A1", "Math");

        when(gameService.startNewGame(eq(1L),any(GameCreateRequestDto.class))).thenReturn(mockResponse);

        GameResponseDto mockFinishedResponse = new GameResponseDto(7, ResultStatus.GOOD);
        when(gameService.finishGame(1L)).thenReturn(mockFinishedResponse);

    }

    @Test
    void startNewGameReturnGameCreateResponse() throws Exception {
        String requestJSON = """
                {
                "numbersOfCards" : 1,
                "level" : "A1",
                "topic" : "Math"
                }
                """;

        mockMvc.perform(post("/api/games/start")
                        .param("studentId","1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                    {
                                "id": 1,
                                "numbersOfCards": 1,
                                "numbersOfRightAnswer": 0,
                                "status" : "IN_PROGRESS",
                                "level" : "A1"
                     }
                                """
                ));
    }

    @Test
    void finishGame() throws Exception {
        mockMvc.perform(get("/api/games/finish")
                        .param("gameId","1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{
"status":"GOOD",
"numbersOfRightAnswer": 7
}
"""));
    }
}