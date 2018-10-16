package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.repository.GameRepository;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class GameService {

    private GameRepository gameRepository;
    private PitRepository pitRepository;

    public GameService(GameRepository gameRepository, PitRepository pitRepository) {
        this.gameRepository = gameRepository;
        this.pitRepository = pitRepository;
    }

    public Game createGame() {
        Game kalahGame = this.gameRepository.save(new Game());
        IntStream.rangeClosed(1,14).forEach(
                n-> {
                    int stones = n % 7 == 0 ? 0 : 6;
                    Boolean isAvailable = n % 7 != 0;
                    pitRepository.save(new Pit(n, kalahGame.getId(), stones, isAvailable));
                }
        );
        return kalahGame;
    }

}