package com.iteratec.codingdojo.openapi.shelter.delegates;

import com.iteratec.codingdojo.openapi.shelter.generated.api.ShelterApiDelegate;
import com.iteratec.codingdojo.openapi.shelter.generated.model.RegisterShelteredAnimal201ResponseDto;
import com.iteratec.codingdojo.openapi.shelter.generated.model.RegisterShelteredAnimalRequestDto;
import com.iteratec.codingdojo.openapi.shelter.generated.model.ShelteredAnimalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ShelterDelegateImpl implements ShelterApiDelegate {

    @Override
    public ResponseEntity<ShelteredAnimalDto> fetchAnimalById(UUID animalId) {
        return ShelterApiDelegate.super.fetchAnimalById(animalId);
    }

    @Override
    public ResponseEntity<List<ShelteredAnimalDto>> fetchShelteredAnimals() {
        return ShelterApiDelegate.super.fetchShelteredAnimals();
    }

    @Override
    public ResponseEntity<RegisterShelteredAnimal201ResponseDto> registerShelteredAnimal(RegisterShelteredAnimalRequestDto registerShelteredAnimalRequestDto) {
        return ShelterApiDelegate.super.registerShelteredAnimal(registerShelteredAnimalRequestDto);
    }
}
