package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.exceptions.InvalidMoveException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GameEngine {
    /*
    * This is the main function for moving the stones in each pit
    * Rules.
    * 1. each player only gets a turn if they placed their last stone in their kalah
    *
     */
    public List<Pit> tryMove(List<Pit> currentPitList, Integer pitId) throws InvalidMoveException {
        //Checks if the pits are available for the move
        if (!currentPitList.get(pitId - 1).getIsAvailable()) {
            throw new InvalidMoveException();
        }

        List<Pit> newPits = new ArrayList<>(currentPitList.size());
        for (Pit item : currentPitList)
            newPits.add(new Pit(item.getId(), item.getGameId(), item.getStoneCount()));

        //Since index starts with Zero
        Pit initialPit = newPits.get(pitId - 1);

        List<Integer> pitIds = getPitIds(pitId, initialPit);

        for (Integer id : pitIds) {
            Pit currentPit = newPits.get(id - 1);
            currentPit.setStoneCount(currentPit.getStoneCount() + 1);
        }

        // Sets the pit stone count to 0
        initialPit.setStoneCount(0);

        //Calculate which pits are available for the next move
        calculateAvailablePits(newPits, pitIds.get(pitIds.size() - 1), pitId);

        return newPits;
    }

    /*
     * This function calculate which pits are available for the next move based on some rules
     * 1. kalah's are never available for move
     * 2. Players can only get turns if they placed their last stone in the opponents pit
     */
    private void calculateAvailablePits(List<Pit> pits, Integer finalPitId, Integer currentPitId) {
        for (int idx = 0; idx <= 13; idx++) {
            if (finalPitId == 7 && idx < 6 && pits.get(idx).getStoneCount() > 0) {
                pits.get(idx).setIsAvailable(true);
            } else if (finalPitId == 14 && idx < 13 && idx > 6 && pits.get(idx).getStoneCount() > 0) {
                pits.get(idx).setIsAvailable(true);
            } else if (finalPitId == 7 && idx > 6) {
                pits.get(idx).setIsAvailable(false);
            } else if (finalPitId == 14 && idx < 6) {
                pits.get(idx).setIsAvailable(false);
            } else if (currentPitId < 7 && idx < 13 && idx > 6 && pits.get(idx).getStoneCount() > 0) {
                pits.get(idx).setIsAvailable(true);
            } else if (currentPitId > 7 && idx < 6 && pits.get(idx).getStoneCount() > 0) {
                pits.get(idx).setIsAvailable(true);
            } else {
                pits.get(idx).setIsAvailable(false);
            }
        }
    }
    /*
     * This function gets the pitIds required for the stones to be moved based on some rules
     * 1. Players should only place stones in the pits and skip opponents kalah
     */
    private List<Integer> getPitIds(Integer pitId, Pit initialPit) {
        Integer startAt = pitId + 1;
        Integer endAt = pitId + initialPit.getStoneCount();
        List<Integer> list = IntStream.rangeClosed(startAt, endAt).boxed().collect(Collectors.toList());

        // skip 14th if pitId < 7
        // skip 7th if pitId > 7, but not the 14th
        Predicate<Integer> skippingPredicate = pitId < 7
                ? id -> id % 14 == 0
                : id -> id % 7 == 0 && id % 14 != 0;
        Integer skipped = Math.toIntExact(list.stream().filter(skippingPredicate).count());
        endAt += skipped;

        List<Integer> pitIds = IntStream.rangeClosed(startAt, endAt).map(id -> {
            Integer times = id / 14;
            Integer newId = id - (14 * times);
            return newId == 0 ? 14 : newId;
        }).boxed().collect(Collectors.toList());

        Predicate<Integer> filterPredicate = pitId < 7 ? p -> p != 14 : p -> p != 7;
        return pitIds.stream().filter(filterPredicate).collect(Collectors.toList());
    }
}
