package com.iteratec.codingdojo.openapi.zentral.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnimalDao extends CrudRepository<AnimalEntity, Integer> {

    Optional<AnimalEntity> findFirstByName(String name);

}
