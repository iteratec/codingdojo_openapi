package com.iteratec.codingdojo.openapi.shelter.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface BoxDao extends CrudRepository<Box, Integer> {

    Optional<Box> findFirstByOccupantNull();

    Optional<Box> findFirstByOccupant_ReferenceId(UUID reference);
}
