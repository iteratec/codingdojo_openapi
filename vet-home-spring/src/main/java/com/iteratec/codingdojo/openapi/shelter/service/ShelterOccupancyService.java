package com.iteratec.codingdojo.openapi.shelter.service;

import com.iteratec.codingdojo.openapi.shelter.connectors.VetZentralConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShelterOccupancyService {

    @Autowired
    VetZentralConnector vetZentralConnector;

    public boolean hasSpaceFor(String animalName) {
        log.info("Looking for animal with name {}", animalName);
        var animal = vetZentralConnector.findByName(animalName);
        return animal.isPresent() && animal.get().getPet();
    }

}
