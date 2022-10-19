package com.iteratec.codingdojo.openapi.shelter.delegates;

import com.iteratec.codingdojo.openapi.shelter.generated.api.AdoptionApiDelegate;
import com.iteratec.codingdojo.openapi.shelter.generated.model.AdoptAnimal200ResponseDto;
import com.iteratec.codingdojo.openapi.shelter.generated.model.AdoptAnimalRequestDto;
import com.iteratec.codingdojo.openapi.shelter.generated.model.AdoptedAnimalDto;
import com.iteratec.codingdojo.openapi.shelter.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AdoptionDelegateImpl implements AdoptionApiDelegate {

    @Autowired
    AdoptionService adoptionService;


    @Override
    public ResponseEntity<AdoptAnimal200ResponseDto> adoptAnimal(UUID animalId, AdoptAnimalRequestDto adoptAnimalRequestDto) {

        return AdoptionApiDelegate.super.adoptAnimal(animalId, adoptAnimalRequestDto);
    }

    @Override
    public ResponseEntity<List<AdoptedAnimalDto>> fetchAdoptedAnimals() {
        return AdoptionApiDelegate.super.fetchAdoptedAnimals();
    }
}
