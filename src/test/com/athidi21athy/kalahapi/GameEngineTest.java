package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.domain.Pit;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameEngineTest {

    @Test
    public void tryMove_from_initial_pits_returns_expected_pits() {
        int gameId = 123;
        List<Pit> pits = getInitialGamePits(gameId);
        List<Pit> expected = getInitialMovePits(gameId);

        List<Pit> actual = new GameEngine().tryMove(pits, 1);
        Assertions.assertThat(actual).extracting("id").containsExactly(expected.stream().map(Pit::getId).toArray());
        Assertions.assertThat(actual).extracting("gameId").allMatch(g -> g.equals(gameId));
        Assertions.assertThat(actual).extracting("stoneCount").containsExactly(expected.stream().map(Pit::getStoneCount).toArray());
    }

    @Test
    public void tryMove_from_initial_turn_returns_expected_pits() {
        int gameId = 12;
        List<Pit> pits = getInitialMovePits(gameId);
        List<Pit> expected = getSecondMovePits(gameId);

        List<Pit> actual = new GameEngine().tryMove(pits, 4);

        // assert
        Assertions.assertThat(actual).extracting("id").containsExactly(expected.stream().map(Pit::getId).toArray());
        Assertions.assertThat(actual).extracting("gameId").allMatch(g -> g.equals(gameId));
        Assertions.assertThat(actual).extracting("stoneCount").containsExactly(expected.stream().map(Pit::getStoneCount).toArray());
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