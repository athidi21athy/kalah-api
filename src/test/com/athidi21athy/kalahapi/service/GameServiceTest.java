package com.athidi21athy.kalahapi.service;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.repository.GameRepository;
import com.athidi21athy.kalahapi.repository.PitRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PitRepository pitRepository;

    private GameService gameService;

    @Before
    public void setUp() throws Exception {
        gameService = new GameService(gameRepository, pitRepository);
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

    @Test
    public void createGame_instantiates_newPits() {
        Game game = new Game();
        game.setId(1);
        given(gameRepository.save(any(Game.class))).willReturn(game);

        gameService.createGame();

        // verify
        IntStream.rangeClosed(1, 14).forEach(
                idx -> {
                    int stones = idx % 7 == 0 ? 0 : 6;
                    verify(pitRepository).save(argThat(p ->
                            p.getId() == idx &&
                                    p.getGameId() == game.getId() &&
                                    p.getStoneCount() == stones)
                    );
                }
        );
    }
}