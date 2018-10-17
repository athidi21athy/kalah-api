package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.GameEngine;
import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.exceptions.InvalidMoveException;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PitService {

    @Autowired
    private final GameEngine gameEngine;
    private PitRepository pitRepository;

    public PitService(PitRepository repository, GameEngine gameEngine) {
        this.pitRepository = repository;
        this.gameEngine = gameEngine;
    }

    //This function will move the stones in the correct position for you after each game
    public Map<String, String> move(Integer gameId, Integer pitId) throws InvalidMoveException {
        //Find the pit using game id
        List<Pit> currentPitList = this.pitRepository.findByGameId(gameId);
        //make the moves
        List<Pit> newPitList = this.gameEngine.tryMove(currentPitList, pitId);
        //saves the pit's latest statues
        pitRepository.saveAll(newPitList);
        return newPitList.stream().collect(
                Collectors.toMap(
                        p -> String.valueOf(p.getId()), p -> String.valueOf(p.getStoneCount())));
    }
}

