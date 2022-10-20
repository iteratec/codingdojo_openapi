package com.iteratec.codingdojo.openapi.shelter.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// using uuid as id type here, even though it should be Integer as the query builder from JPA/Hibernate is bugged.
public interface OccupantsDao extends CrudRepository<Occupant, UUID> {

    List<Occupant> findAllByAdoptionDateNotNull();

    Optional<Occupant> findFirstByReferenceId(UUID referenceId);
}
