package com.iteratec.codingdojo.openapi.shelter.service;

import com.iteratec.codingdojo.openapi.shelter.data.BoxDao;
import com.iteratec.codingdojo.openapi.shelter.data.Occupant;
import com.iteratec.codingdojo.openapi.shelter.data.OccupantsDao;
import com.iteratec.codingdojo.openapi.shelter.errors.UnknownAnimalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Component
public class AdoptionService {

    @Autowired
    OccupantsDao occupantsDao;
    @Autowired
    BoxDao boxDao;

    public String adopt(UUID animal) throws UnknownAnimalException {
        var b = boxDao.findFirstByOccupant_ReferenceId(animal)
                .orElseThrow(UnknownAnimalException::new);

        var occupant = b.getOccupant();
        occupant.setAdoptionDate(Calendar.getInstance());
        occupantsDao.save(occupant);

        b.setOccupant(null);
        boxDao.save(b);

        return b.getShelter().getPlz();
    }

    public List<Occupant> fetchAdoptedAnimals() {
        var list = new ArrayList<Occupant>();
        list.addAll(occupantsDao.findAllByAdoptionDateNotNull());
        return list;
    }
}
