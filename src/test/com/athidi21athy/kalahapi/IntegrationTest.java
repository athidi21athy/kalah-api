package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.domain.Game;
import com.athidi21athy.kalahapi.domain.GameState;
import com.athidi21athy.kalahapi.domain.Pit;
import jdk.nashorn.internal.objects.Global;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static jdk.nashorn.internal.objects.Global.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createGame_returnsGameDetails() throws Exception {
        // arrange

        // act
        ResponseEntity<Game> response = restTemplate.postForEntity("/games", null, Game.class);

        // assert
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody().getId()).isInstanceOfAny(Integer.class);
        Assertions.assertThat(response.getBody().getUri()).isEqualTo("/games/" + response.getBody().getId());
    }

    @Test
    public void putPit_returnsExpected() throws Exception {
        //arrange
        ResponseEntity<Game> gameResponse = restTemplate.postForEntity("/games", null, Game.class);

        //act
        final String url = "/games/{gameId}/pits/{pitId}";
        Map<String, Integer> param = new HashMap<>();
        param.put("gameId", gameResponse.getBody().getId());
        param.put("pitId", 1);
        ResponseEntity<GameState> response = restTemplate.exchange(url, HttpMethod.PUT, HttpEntity.EMPTY, GameState.class, param);

        //assert
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getId()).isEqualTo(gameResponse.getBody().getId());
        Assertions.assertThat(response.getBody().getUri()).isEqualTo(gameResponse.getBody().getUri());
        Map<String, String> expectedState = new HashMap<>();
        expectedState.put("1", "0");
        expectedState.put("2", "7");
        expectedState.put("3", "7");
        expectedState.put("4", "7");
        expectedState.put("5", "7");
        expectedState.put("6", "7");
        expectedState.put("7", "1");
        expectedState.put("8", "6");
        expectedState.put("9", "6");
        expectedState.put("10", "6");
        expectedState.put("11", "6");
        expectedState.put("12", "6");
        expectedState.put("13", "6");
        expectedState.put("14", "0");
        Assertions.assertThat(response.getBody().getState()).isEqualTo(expectedState);
    }
}
