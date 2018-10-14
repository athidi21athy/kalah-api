package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.controller.GameController;
import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.service.GameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    public void postGame_shouldReturnGame() throws Exception {
        Game game = new Game();
        game.setId(new Random().nextInt(100));
        given(gameService.createGame()).willReturn(game);

        mockMvc.perform(MockMvcRequestBuilders.post("/games"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(game.getId()))
                .andExpect(jsonPath("uri").value(game.getUri()));
    }
}
