package com.iteratec.codingdojo.openapi.shelter.delegates;

import com.iteratec.codingdojo.openapi.shelter.generated.api.ShelterApiDelegate;
import com.iteratec.codingdojo.openapi.shelter.generated.model.RegisterShelteredAnimal201ResponseDto;
import com.iteratec.codingdojo.openapi.shelter.generated.model.RegisterShelteredAnimalRequestDto;
import com.iteratec.codingdojo.openapi.shelter.generated.model.ShelteredAnimalDto;
import com.iteratec.codingdojo.openapi.shelter.service.ShelterOccupancyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ShelterDelegateImpl implements ShelterApiDelegate {

    @Autowired
    ShelterOccupancyService occupancyService;

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
        if (!occupancyService.hasSpaceFor(registerShelteredAnimalRequestDto.getName())) {
            log.info("Could not accept the animal.");
            return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
        }


        return ShelterApiDelegate.super.registerShelteredAnimal(registerShelteredAnimalRequestDto);
    }
}
