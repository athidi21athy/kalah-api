package com.athidi21athy.kalahapi.controller;

import com.athidi21athy.kalahapi.domain.GameState;
import com.athidi21athy.kalahapi.exceptions.InvalidMoveException;
import com.athidi21athy.kalahapi.service.PitService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PitController {

    private PitService pitService;

    public PitController(PitService pitService) {
        this.pitService = pitService;
    }

    @PutMapping("/games/{gameId}/pits/{pitId}")
    private GameState updatePit(@PathVariable("gameId") Integer gameId, @PathVariable("pitId") Integer pitId) {
        Map<String, String> state = pitService.move(gameId, pitId);
        return new GameState(gameId, state);
    }

}
