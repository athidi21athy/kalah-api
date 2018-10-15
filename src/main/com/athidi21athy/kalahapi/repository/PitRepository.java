package com.athidi21athy.kalahapi.repository;

import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.domain.PitIdentifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PitRepository extends CrudRepository<Pit, PitIdentifier> {
    List<Pit> findByGameId(Integer gameId);
}
