package com.athidi21athy.kalahapi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(PitIdentifier.class)
public class Pit {

    @Id
    private int id;
    @Id
    private int gameId;
    private int stones;
    private boolean isAvailable;

    protected Pit() {
    }

    public Pit(int id, int gameId, int stoneCount) {
        this(id, gameId, stoneCount, true);
    }

    public Pit(int id, int gameId, int stoneCount, boolean isAvailable) {
        this.id = id;
        this.gameId = gameId;
        this.stones = stoneCount;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return this.id;
    }

    public int getGameId() {
        return this.gameId;
    }

    public int getStoneCount() {
        return this.stones;
    }

    public void setStoneCount(int count) {
        this.stones = count;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
