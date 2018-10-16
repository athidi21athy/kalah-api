package com.athidi21athy.kalahapi.controller;

import com.athidi21athy.kalahapi.exceptions.InvalidMoveException;
import com.athidi21athy.kalahapi.service.PitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PitController.class)
public class PitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PitService pitService;

    @Test
    public void putPit_on_new_game_shouldReturn_expected_GameState() throws Exception, InvalidMoveException {
        Map<String, String> state = new HashMap<>();
        state.put("1", "0");
        state.put("2", "7");
        given(pitService.move(1, 1)).willReturn(state);

        mockMvc.perform(MockMvcRequestBuilders.put("/games/{gameId}/pits/{pitId}", 1, 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("uri").value("/games/1"))
                .andExpect(jsonPath("state").value(state));
    }
}

