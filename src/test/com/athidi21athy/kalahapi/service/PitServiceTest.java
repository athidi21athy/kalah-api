package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.controller.GameController;
import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PitServiceTest {

    @Mock
    private PitRepository pitReppository;

    @Mock
    private PitService pitService;

    @Before
    public void setUp() throws Exception {
        pitService = new PitService(pitReppository);
    }

    @Test
    public void move_for_initial_game_returns_expected_state() {
        Game game = new Game();
        game.setId(new Random().nextInt(100));

        //find the pit id using the game id
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, game.getId(), 6));
        pits.add(new Pit(2, game.getId(), 6));
        pits.add(new Pit(3, game.getId(), 6));
        pits.add(new Pit(4, game.getId(), 6));
        pits.add(new Pit(5, game.getId(), 6));
        pits.add(new Pit(6, game.getId(), 6));
        pits.add(new Pit(7, game.getId(), 0));
        pits.add(new Pit(8, game.getId(), 6));
        pits.add(new Pit(9, game.getId(), 6));
        pits.add(new Pit(10, game.getId(), 6));
        pits.add(new Pit(11, game.getId(), 6));
        pits.add(new Pit(12, game.getId(), 6));
        pits.add(new Pit(13, game.getId(), 6));
        pits.add(new Pit(14, game.getId(), 0));
        given(pitReppository.findByGameId(game.getId())).willReturn(pits);

        Map<String, String> expected = new HashMap<>();
        expected.put("1", "0");
        expected.put("2", "7");
        expected.put("3", "7");
        expected.put("4", "7");
        expected.put("5", "7");
        expected.put("6", "7");
        expected.put("7", "1");
        expected.put("8", "6");
        expected.put("9", "6");
        expected.put("10", "6");
        expected.put("11", "6");
        expected.put("12", "6");
        expected.put("13", "6");
        expected.put("14", "0");

        // act
        Map<String, String> state = pitService.move(game.getId(), 1);

        // assert
        assertThat(state).isEqualTo(expected);
    }
}
