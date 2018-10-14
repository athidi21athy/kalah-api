package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame() {
        return this.gameRepository.save(new Game());
    }
}