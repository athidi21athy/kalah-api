package com.athidi21athy.kalahapi.repository;

import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.domain.PitIdentifier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//This repository ise used to find the pit using the game id
public interface PitRepository extends CrudRepository<Pit, PitIdentifier> {
    List<Pit> findByGameId(Integer gameId);
}
