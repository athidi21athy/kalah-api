package com.athidi21athy.kalahapi;

import com.athidi21athy.kalahapi.domain.Game;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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
}
