package com.athidi21athy.kalahapi.controller;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

    /*
     * This is the controller class for the game. This class will create the game.
     */

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
    private ResponseEntity<Game> postGame() {
        Game body = gameService.createGame();
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
