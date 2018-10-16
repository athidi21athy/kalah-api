package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.domain.Pit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameEngine {
    public List<Pit> tryMove(List<Pit> currentPitList, Integer pitId) {
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

        initialPit.setStoneCount(0);
        return newPits;
    }

    private List<Integer> getPitIds(Integer pitId, Pit initialPit) {
        Integer startAt = pitId + 1;
        Integer endAt = pitId + initialPit.getStoneCount();
        List<Integer> list = IntStream.rangeClosed(startAt, endAt).boxed().collect(Collectors.toList());
        Integer skipped = Math.toIntExact(list.stream().filter(id -> id % 14 == 0).count());
        endAt += skipped;

        List<Integer> pitIds = IntStream.rangeClosed(startAt, endAt).map(id -> {
            Integer times = id / 14;
            Integer newId = id - (14 * times);
            return newId == 0 ? 14 : newId;
        }).boxed().collect(Collectors.toList());

        return pitIds.stream().filter(p -> p != 14).collect(Collectors.toList());
    }
}
