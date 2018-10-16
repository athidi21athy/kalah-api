package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.domain.Pit;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    public List<Pit> tryMove(List<Pit> currentPitList, Integer pitId) {
        List<Pit> newPits = new ArrayList<>(currentPitList.size());
        for (Pit item : currentPitList)
            newPits.add(new Pit(item.getId(), item.getGameId(), item.getStoneCount()));

        //Since index starts with Zero
        Pit initialPit = newPits.get(pitId - 1);
        //go through the list of pits and move each stones to the pit on the right hand side
        //get Stone count from the pit
        for (int i = pitId; i < pitId + initialPit.getStoneCount(); i++) {
            Pit currentPit = newPits.get(i);
            currentPit.setStoneCount(currentPit.getStoneCount() + 1);
        }

        initialPit.setStoneCount(0);
        return newPits;
    }
}
