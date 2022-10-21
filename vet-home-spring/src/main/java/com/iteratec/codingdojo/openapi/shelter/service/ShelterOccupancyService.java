package com.iteratec.codingdojo.openapi.shelter.service;

import com.iteratec.codingdojo.openapi.shelter.connectors.VetZentralConnector;
import com.iteratec.codingdojo.openapi.shelter.data.BoxDao;
import com.iteratec.codingdojo.openapi.shelter.data.Occupant;
import com.iteratec.codingdojo.openapi.shelter.data.OccupantsDao;
import com.iteratec.codingdojo.openapi.shelter.errors.NoSpaceException;
import com.iteratec.codingdojo.openapi.shelter.errors.UnknownAnimalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class ShelterOccupancyService {

    @Autowired
    VetZentralConnector vetZentralConnector;
    @Autowired
    BoxDao boxDao;
    @Autowired
    OccupantsDao occupantsDao;

    public UUID reserveBoxFor(String animalName, Date birthDay) throws NoSpaceException, UnknownAnimalException {
        log.info("Looking for animal with name {}", animalName);
        var animal = vetZentralConnector.findByName(animalName);
        if (animal.isEmpty()) {
            throw new UnknownAnimalException();
        } else if (Boolean.FALSE.equals(animal.get().getPet())) {
            log.warn("Could not reserve box for animal that is not a pet.");
            throw new NoSpaceException();
        }
        var box = boxDao.findFirstByOccupantNull()
                        .orElseThrow(NoSpaceException::new);

        var reservationRef = UUID.randomUUID();

        box.setOccupant(Occupant.builder()
                        .animalId(animal.get().getId())
                        .animal(animal.get().getName())
                        .birthDay(birthDay)
                        .referenceId(reservationRef)
                        .build());
        occupantsDao.save(box.getOccupant());
        boxDao.save(box);

        return reservationRef;
    }

    public List<Occupant> currentOccupants() {
        var list = occupantsDao.findAll().iterator();
        return Stream.generate(() -> null)
                .takeWhile(m -> list.hasNext())
                .map(n -> list.next())
                .collect(Collectors.toList());
    }

    public Optional<Occupant> fetchOccupant(UUID reference) {
        return occupantsDao.findFirstByReferenceId(reference);
    }
}
