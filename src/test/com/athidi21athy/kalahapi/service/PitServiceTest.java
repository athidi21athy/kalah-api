package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
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
        List<Pit> pits = getInitialGamePits(game.getId());
        given(pitReppository.findByGameId(game.getId())).willReturn(pits);

        List<Pit> initialMovePits = getInitialMovePits(game.getId());
        Map<String, String> expected = initialMovePits.stream().collect(Collectors.toMap(p -> p.getId().toString(), p -> p.getStoneCount().toString()));

        // act
        Map<String, String> state = pitService.move(game.getId(), 1);

        // assert
        assertThat(state).isEqualTo(expected);
    }

    @Test
    public void move_for_second_turn_returns_expected_state() {
        Integer gameId = 12;
        List<Pit> pits = getInitialMovePits(gameId);
        given(pitReppository.findByGameId(gameId)).willReturn(pits);

        List<Pit> secondMovePits = getSecondMovePits(gameId);
        Map<String, String> expected = secondMovePits.stream().collect(Collectors.toMap(p -> p.getId().toString(), p -> p.getStoneCount().toString()));

        Map<String, String> state = pitService.move(gameId, 4);

        // assert
        assertThat(state).isEqualTo(expected);
    }

    @Test
    public void move_saves_the_new_Pits() {
        Integer gameId = 10;
        List<Pit> pits = getInitialGamePits(gameId);
        given(pitReppository.findByGameId(gameId)).willReturn(pits);

        pitService.move(gameId, 1);

        verify(pitReppository).saveAll(pits);
    }

    private List<Pit> getInitialMovePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 0));
        pits.add(new Pit(2, gameId, 7));
        pits.add(new Pit(3, gameId, 7));
        pits.add(new Pit(4, gameId, 7));
        pits.add(new Pit(5, gameId, 7));
        pits.add(new Pit(6, gameId, 7));
        pits.add(new Pit(7, gameId, 1));
        pits.add(new Pit(8, gameId, 6));
        pits.add(new Pit(9, gameId, 6));
        pits.add(new Pit(10, gameId, 6));
        pits.add(new Pit(11, gameId, 6));
        pits.add(new Pit(12, gameId, 6));
        pits.add(new Pit(13, gameId, 6));
        pits.add(new Pit(14, gameId, 0));
        return pits;
    }

    private List<Pit> getSecondMovePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 0));
        pits.add(new Pit(2, gameId, 7));
        pits.add(new Pit(3, gameId, 7));
        pits.add(new Pit(4, gameId, 0));
        pits.add(new Pit(5, gameId, 8));
        pits.add(new Pit(6, gameId, 8));
        pits.add(new Pit(7, gameId, 2));
        pits.add(new Pit(8, gameId, 7));
        pits.add(new Pit(9, gameId, 7));
        pits.add(new Pit(10, gameId, 7));
        pits.add(new Pit(11, gameId, 7));
        pits.add(new Pit(12, gameId, 6));
        pits.add(new Pit(13, gameId, 6));
        pits.add(new Pit(14, gameId, 0));
        return pits;
    }

    private List<Pit> getInitialGamePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 6));
        pits.add(new Pit(2, gameId, 6));
        pits.add(new Pit(3, gameId, 6));
        pits.add(new Pit(4, gameId, 6));
        pits.add(new Pit(5, gameId, 6));
        pits.add(new Pit(6, gameId, 6));
        pits.add(new Pit(7, gameId, 0));
        pits.add(new Pit(8, gameId, 6));
        pits.add(new Pit(9, gameId, 6));
        pits.add(new Pit(10, gameId, 6));
        pits.add(new Pit(11, gameId, 6));
        pits.add(new Pit(12, gameId, 6));
        pits.add(new Pit(13, gameId, 6));
        pits.add(new Pit(14, gameId, 0));
        return pits;
    }
}
