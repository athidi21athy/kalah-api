package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.GameEngine;
import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PitService {

    private final GameEngine gameEngine;
    private PitRepository pitRepository;

    public PitService(PitRepository repository, GameEngine gameEngine) {
        this.pitRepository = repository;
        this.gameEngine = gameEngine;
    }

    //This function will move the stones in the correct position for you after each
    public Map<String, String> move(Integer gameId, Integer pitId) {
        //On a move should return expected State
        List<Pit> currentPitList = this.pitRepository.findByGameId(gameId);
        List<Pit> newPitList = this.gameEngine.tryMove(currentPitList, pitId);
        pitRepository.saveAll(newPitList);
        return newPitList.stream().collect(
                Collectors.toMap(
                        p -> String.valueOf(p.getId()), p -> String.valueOf(p.getStoneCount())));
    }
}

