package com.athidi21athy.kalahapi.controller;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/games")
    private ResponseEntity<Game> postGame() {
        Game body = gameService.createGame();
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
