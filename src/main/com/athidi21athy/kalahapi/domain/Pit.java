package com.athidi21athy.kalahapi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PitIdentifier.class)
public class Pit {

    @Id
    private Integer id;
    @Id
    private Integer gameId;
    private Integer stones;

    protected Pit() {
    }

    public Pit(Integer id, Integer gameId, Integer stoneCount) {
        this.id = id;
        this.gameId = gameId;
        this.stones = stoneCount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public Integer getStoneCount() {
        return stones;
    }

    public void setStoneCount(Integer count) {
        stones = count;
    }
}
