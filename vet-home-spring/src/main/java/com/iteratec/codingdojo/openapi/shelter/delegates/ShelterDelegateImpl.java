package com.iteratec.codingdojo.openapi.shelter.delegates;

import com.iteratec.codingdojo.openapi.shelter.data.Occupant;
import com.iteratec.codingdojo.openapi.shelter.errors.NoSpaceException;
import com.iteratec.codingdojo.openapi.shelter.errors.UnknownAnimalException;
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
import java.util.stream.Collectors;

@Component
@Slf4j
public class ShelterDelegateImpl implements ShelterApiDelegate {

    @Autowired
    ShelterOccupancyService occupancyService;

    @Override
    public ResponseEntity<ShelteredAnimalDto> fetchAnimalById(UUID animalId) {
        var animal = occupancyService.fetchOccupant(animalId);
        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .headers(OpenApiTypeConversionHelper.getHttpHeadersConsumer())
                .body(mapShelteredAnimal(animal.get()));
    }

    @Override
    public ResponseEntity<List<ShelteredAnimalDto>> fetchShelteredAnimals() {
        log.info("Got a request for all currently sheltered animals.");
        var animals = occupancyService.currentOccupants().stream()
                .map(this::mapShelteredAnimal)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(OpenApiTypeConversionHelper.getHttpHeadersConsumer())
                .body(animals);
    }

    @Override
    public ResponseEntity<RegisterShelteredAnimal201ResponseDto> registerShelteredAnimal(RegisterShelteredAnimalRequestDto registerShelteredAnimalRequestDto) {
        log.info("Got a request for shelter for [{}]", registerShelteredAnimalRequestDto.getName());
        try {
            var reservationId = occupancyService.reserveBoxFor(registerShelteredAnimalRequestDto.getName(),
                    OpenApiTypeConversionHelper.map(registerShelteredAnimalRequestDto.getBirthdate()));
            return ResponseEntity.ok().headers(OpenApiTypeConversionHelper.getHttpHeadersConsumer())
                    .body(new RegisterShelteredAnimal201ResponseDto().registeredId(reservationId));
        } catch (NoSpaceException e) {
            return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
        } catch (UnknownAnimalException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private ShelteredAnimalDto mapShelteredAnimal(Occupant occupant) {
        return new ShelteredAnimalDto().id(occupant.getReferenceId())
                .birthDate(OpenApiTypeConversionHelper.map(occupant.getBirthDay())).name(occupant.getAnimal())
                .readyForAdoption(occupant.getAdoptionDate() == null)
                .registered(OpenApiTypeConversionHelper.map(occupant.getShelterDate()));
    }

}
