package com.iteratec.codingdojo.openapi.shelter.delegates;

import com.iteratec.codingdojo.openapi.shelter.errors.UnknownAnimalException;
import com.iteratec.codingdojo.openapi.shelter.generated.api.AdoptionApiDelegate;
import com.iteratec.codingdojo.openapi.shelter.generated.model.*;
import com.iteratec.codingdojo.openapi.shelter.service.AdoptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class AdoptionDelegateImpl implements AdoptionApiDelegate {

    @Autowired
    AdoptionService adoptionService;

    @Override
    public ResponseEntity<AdoptAnimal200ResponseDto> adoptAnimal(UUID animalId, AdoptAnimalRequestDto adoptAnimalRequestDto) {
        log.info("Got a request to adopt the animal [{}]", animalId);
        try {
            var plz = adoptionService.adopt(animalId);
            log.info("Adoption approved for [{}] from [{}]", adoptAnimalRequestDto.getAdopteeName(),
                    adoptAnimalRequestDto.getAdopteePlz());
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(OpenApiTypeConversionHelper.getHttpHeadersConsumer())
                    .body(new PositiveAdoptionAnswerDto()
                            .pickupShelterPlz(plz));
        } catch (UnknownAnimalException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new NegativeAdoptionAnswerDto()
                    .rejectReason("The referenced animal is no longer in the shelter."));
        }
    }


    @Override
    public ResponseEntity<List<AdoptedAnimalDto>> fetchAdoptedAnimals() {
        log.info("Got a request to fetch all adopted animals.");
        // TODO this needs to be implemented based on the open api definition.
        return AdoptionApiDelegate.super.fetchAdoptedAnimals();
    }
}
