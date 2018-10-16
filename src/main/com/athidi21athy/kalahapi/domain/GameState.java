package com.athidi21athy.kalahapi.domain;

import java.util.Map;

/*
 * This class stores the gameState. Based on the game id it gives you the stone count
*/
public class GameState {
    private Map<String, String> state;
    private String uri;
    private Integer id;

    protected GameState() {}

    public GameState(Integer gameId, Map<String, String> state) {
        this.id = gameId;
        this.uri = "/games/" + gameId;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public Map<String, String> getState() {
        return state;
    }
}
