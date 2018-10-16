package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.GameEngine;
import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PitServiceTest {

    @Mock
    private PitRepository pitRepository;

    @Mock
    private PitService pitService;
    @Mock
    private GameEngine gameEngine;

    @Before
    public void setUp() throws Exception {
        pitService = new PitService(pitRepository, gameEngine);
    }

    @Test
    public void move_for_initial_game_returns_expected_state_from_gameEngine() {
        int gameId = new Random().nextInt(100);

        //find the pit id using the game id
        List<Pit> pits = Collections.singletonList(new Pit(1, gameId, 6));
        List<Pit> initialMovePits = Collections.singletonList(new Pit(1, gameId, 0));
        given(pitRepository.findByGameId(gameId)).willReturn(pits);
        given(gameEngine.tryMove(pits, 1)).willReturn(initialMovePits);

        Map<String, String> expected = initialMovePits.stream()
                .collect(Collectors.toMap(p -> String.valueOf(p.getId()), p -> String.valueOf(p.getStoneCount())));

        // act
        Map<String, String> state = pitService.move(gameId, 1);

        // assert
        assertThat(state).isEqualTo(expected);
    }

    @Test
    public void move_saves_the_new_Pits() {
        int gameId = 10;
        List<Pit> pits = Collections.singletonList(new Pit(1, gameId, 6));
        List<Pit> newPits = Collections.singletonList(new Pit(1, gameId, 0));
        given(pitRepository.findByGameId(gameId)).willReturn(pits);
        given(gameEngine.tryMove(pits, 1)).willReturn(newPits);

        pitService.move(gameId, 1);

        verify(pitRepository).saveAll(newPits);
    }

}
