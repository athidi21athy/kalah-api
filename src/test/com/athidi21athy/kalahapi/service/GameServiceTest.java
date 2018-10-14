package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    private GameService gameService;

    @Before
    public void setUp() throws Exception {
        gameService = new GameService(gameRepository);
    }

    @Test
    public void createGame_returnsGameInfo() {
        Game game = new Game();
        game.setId(new Random().nextInt(100));
        given(gameRepository.save(any(Game.class))).willReturn(game);

        Game actual = gameService.createGame();

        assertThat(actual.getId()).isEqualTo(game.getId());
        assertThat(actual.getUri()).isEqualTo(game.getUri());
    }
}