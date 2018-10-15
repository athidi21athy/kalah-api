package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.domain.GameState;
import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PitService {

    private PitRepository pitRepository;

    public PitService(PitRepository repository) {
        this.pitRepository = repository;
    }

    public Map<String, String> move(Integer gameId, Integer pitId) {
        //On a move should return expected State
        List<Pit> currentPitList = this.pitRepository.findByGameId(gameId);

        for (int i = 1; i <= 6; i++) {
            Pit currentPit = currentPitList.get(i);
            currentPit.setStoneCount(currentPit.getStoneCount() + 1);
        }

        currentPitList.get(0).setStoneCount(0);

        //go through the list of pits and move each stones to the pit on the right hand side
        //get Stone count from the pit
        return currentPitList.stream().collect(
                Collectors.toMap(
                        p -> p.getId().toString(), p -> p.getStoneCount().toString()));
    }
}
