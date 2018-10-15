package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.springframework.stereotype.Service;

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
        Pit initialPit = currentPitList.get(pitId - 1);

        for (int i = pitId; i < pitId + initialPit.getStoneCount(); i++) {
            Pit currentPit = currentPitList.get(i);
            currentPit.setStoneCount(currentPit.getStoneCount() + 1);
        }

        initialPit.setStoneCount(0);

        pitRepository.saveAll(currentPitList);

        //go through the list of pits and move each stones to the pit on the right hand side
        //get Stone count from the pit
        return currentPitList.stream().collect(
                Collectors.toMap(
                        p -> p.getId().toString(), p -> p.getStoneCount().toString()));
    }
}
