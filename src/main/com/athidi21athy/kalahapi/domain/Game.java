package com.athidi21athy.kalahapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public String getUri() {
        return "/games/" + this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
