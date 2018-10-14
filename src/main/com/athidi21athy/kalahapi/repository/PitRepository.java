package com.athidi21athy.kalahapi.repository;

import com.athidi21athy.kalahapi.domain.Pit;
import com.athidi21athy.kalahapi.domain.PitIdentifier;
import org.springframework.data.repository.CrudRepository;

public interface PitRepository extends CrudRepository<Pit, PitIdentifier> {
}
