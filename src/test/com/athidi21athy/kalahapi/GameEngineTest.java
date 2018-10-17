package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.exceptions.InvalidMoveException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameEngineTest {

    @Test
    public void tryMove_from_initial_pits_returns_expected_pits() throws InvalidMoveException {
        int gameId = 123;
        List<Pit> pits = getInitialGamePits(gameId);
        List<Pit> expected = getInitialMovePits(gameId);

        List<Pit> actual = new GameEngine().tryMove(pits, 1);
        Assertions.assertThat(actual).extracting("id").containsExactly(expected.stream().map(Pit::getId).toArray());
        Assertions.assertThat(actual).extracting("gameId").allMatch(g -> g.equals(gameId));
        Assertions.assertThat(actual).extracting("stoneCount").containsExactly(expected.stream().map(Pit::getStoneCount).toArray());
    }

    @Test
    public void tryMove_from_initial_turn_returns_expected_pits() throws InvalidMoveException {
        int gameId = 12;
        List<Pit> pits = getInitialMovePits(gameId);
        List<Pit> expected = getSecondMovePits(gameId);

        List<Pit> actual = new GameEngine().tryMove(pits, 4);

        // assert
        Assertions.assertThat(actual).extracting("id").containsExactly(expected.stream().map(Pit::getId).toArray());
        Assertions.assertThat(actual).extracting("gameId").allMatch(g -> g.equals(gameId));
        Assertions.assertThat(actual).extracting("stoneCount").containsExactly(expected.stream().map(Pit::getStoneCount).toArray());
    }

    @Test
    public void tryMove_skips_opponents_kalah_pit() throws InvalidMoveException {
        int gameId = 14;
        List<Pit> pits = getThirdMovePits(gameId);
        List<Pit> expected = getFourthMovePits(gameId);

        List<Pit> actual = new GameEngine().tryMove(pits, 6);

        // assert
        Assertions.assertThat(actual).extracting("id").containsExactly(expected.stream().map(Pit::getId).toArray());
        Assertions.assertThat(actual).extracting("gameId").allMatch(g -> g.equals(gameId));
        Assertions.assertThat(actual).extracting("stoneCount").containsExactly(expected.stream().map(Pit::getStoneCount).toArray());
    }

    @Test
    public void tryMove_skips_the_other_opponents_kalah_pit() throws InvalidMoveException {
        int gameId = 14;
        List<Pit> pits = getFourthMovePits(gameId);
        List<Pit> expected = getFifthMovePits(gameId);

        List<Pit> actual = new GameEngine().tryMove(pits, 13);

        // assert
        Assertions.assertThat(actual).extracting("id").containsExactly(expected.stream().map(Pit::getId).toArray());
        Assertions.assertThat(actual).extracting("gameId").allMatch(g -> g.equals(gameId));
        Assertions.assertThat(actual).extracting("stoneCount").containsExactly(expected.stream().map(Pit::getStoneCount).toArray());
    }

    @Test(expected = InvalidMoveException.class)
    public void tryMove_throws_InvalidMoveException_on_making_unavailable_moves() throws InvalidMoveException {
        int gameId = 14;
        int count = 0;
        List<Pit> pits = getInitialGamePits(gameId);
        for(int pitId : Arrays.asList(1, 5, 6, 7, 14)) {
            try {
                pits.get(pitId - 1).setIsAvailable(false);
                new GameEngine().tryMove(pits, pitId);
            } catch (InvalidMoveException ex) {
                count++;
                if (count == 5) {
                    throw ex;
                }
            }
        }
    }

    @Test
    public void tryMove_returnsExpected_pitAvailability_after_initialMove() {
        int gameId = 14;
        List<Pit> pits = getInitialGamePits(gameId);
        List<Pit> actual = new GameEngine().tryMove(pits, 1);
        Assertions.assertThat(actual).extracting("isAvailable").containsExactly(
                false, true, true, true, true, true, false, false, false, false, false, false, false, false);
    }

    @Test
    public void tryMove_returnsExpected_pitAvailability_after_secondMove() {
        int gameId = 14;
        List<Pit> pits = getInitialMovePits(gameId);
        List<Pit> actual = new GameEngine().tryMove(pits, 4);
        Assertions.assertThat(actual).extracting("isAvailable").containsExactly(
                false, false, false, false, false, false, false, true, true, true, true, true, true, false);
    }

    // if pitId is 1
    private List<Pit> getInitialMovePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 0, false));
        pits.add(new Pit(2, gameId, 7, true));
        pits.add(new Pit(3, gameId, 7, true));
        pits.add(new Pit(4, gameId, 7, true));
        pits.add(new Pit(5, gameId, 7, true));
        pits.add(new Pit(6, gameId, 7, true));
        pits.add(new Pit(7, gameId, 1, false));
        pits.add(new Pit(8, gameId, 6, false));
        pits.add(new Pit(9, gameId, 6, false));
        pits.add(new Pit(10, gameId, 6, false));
        pits.add(new Pit(11, gameId, 6, false));
        pits.add(new Pit(12, gameId, 6, false));
        pits.add(new Pit(13, gameId, 6, false));
        pits.add(new Pit(14, gameId, 0, false));
        return pits;
    }

    // if pitId is 4
    private List<Pit> getSecondMovePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 0, false));
        pits.add(new Pit(2, gameId, 7, false));
        pits.add(new Pit(3, gameId, 7, false));
        pits.add(new Pit(4, gameId, 0, false));
        pits.add(new Pit(5, gameId, 8, false));
        pits.add(new Pit(6, gameId, 8, false));
        pits.add(new Pit(7, gameId, 2, false));
        pits.add(new Pit(8, gameId, 7, true));
        pits.add(new Pit(9, gameId, 7, true));
        pits.add(new Pit(10, gameId, 7, true));
        pits.add(new Pit(11, gameId, 7, true));
        pits.add(new Pit(12, gameId, 6, true));
        pits.add(new Pit(13, gameId, 6, true));
        pits.add(new Pit(14, gameId, 0, false));
        return pits;
    }

    // if pitId is 11
    private List<Pit> getThirdMovePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 1));
        pits.add(new Pit(2, gameId, 8));
        pits.add(new Pit(3, gameId, 8));
        pits.add(new Pit(4, gameId, 1));
        pits.add(new Pit(5, gameId, 8));
        pits.add(new Pit(6, gameId, 8));
        pits.add(new Pit(7, gameId, 2));
        pits.add(new Pit(8, gameId, 7));
        pits.add(new Pit(9, gameId, 7));
        pits.add(new Pit(10, gameId, 7));
        pits.add(new Pit(11, gameId, 0));
        pits.add(new Pit(12, gameId, 7));
        pits.add(new Pit(13, gameId, 7));
        pits.add(new Pit(14, gameId, 1));
        return pits;
    }

    // if pitId is 6
    private List<Pit> getFourthMovePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 2));
        pits.add(new Pit(2, gameId, 8));
        pits.add(new Pit(3, gameId, 8));
        pits.add(new Pit(4, gameId, 1));
        pits.add(new Pit(5, gameId, 8));
        pits.add(new Pit(6, gameId, 0));
        pits.add(new Pit(7, gameId, 3));
        pits.add(new Pit(8, gameId, 8));
        pits.add(new Pit(9, gameId, 8));
        pits.add(new Pit(10, gameId, 8));
        pits.add(new Pit(11, gameId, 1));
        pits.add(new Pit(12, gameId, 8));
        pits.add(new Pit(13, gameId, 8));
        pits.add(new Pit(14, gameId, 1));
        return pits;
    }

    // if pitId is 13
    private List<Pit> getFifthMovePits(Integer gameId) {
        List<Pit> pits = new ArrayList<>();
        pits.add(new Pit(1, gameId, 3));
        pits.add(new Pit(2, gameId, 9));
        pits.add(new Pit(3, gameId, 9));
        pits.add(new Pit(4, gameId, 2));
        pits.add(new Pit(5, gameId, 9));
        pits.add(new Pit(6, gameId, 1));
        pits.add(new Pit(7, gameId, 3));
        pits.add(new Pit(8, gameId, 9));
        pits.add(new Pit(9, gameId, 8));
        pits.add(new Pit(10, gameId, 8));
        pits.add(new Pit(11, gameId, 1));
        pits.add(new Pit(12, gameId, 8));
        pits.add(new Pit(13, gameId, 0));
        pits.add(new Pit(14, gameId, 2));
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