package com.iteratec.codingdojo.openapi.zentral.delegates;

import com.iteratec.codingdojo.openapi.zentral.data.AnimalEntity;
import com.iteratec.codingdojo.openapi.zentral.generated.v2.api.V2ApiDelegate;
import com.iteratec.codingdojo.openapi.zentral.generated.v2.model.*;
import com.iteratec.codingdojo.openapi.zentral.service.AnimalLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("V2")
@Slf4j
public class DelegateV2Impl implements V2ApiDelegate {
    @Autowired
    AnimalLibraryService animalLibrary;

    @Override
    public ResponseEntity<DtoAnimalEntry> fetchAnimalTypeById(Integer id) {
        var animal = animalLibrary.fetchAnimal(id);
        if (animal.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapToResp(animal.get()));
    }

    @Override
    public ResponseEntity<List<DtoAnimalEntry>> fetchAnimalTypes() {
        var animals = animalLibrary.fetchAnimals().stream().map(this::mapToResp).collect(Collectors.toList());
        return ResponseEntity.ok(animals);
    }

    @Override
    public ResponseEntity<DtoRegisterAnimalType201Response> registerAnimalType(DtoRegisterAnimalTypeRequest creationRequest) {
        if (!animalLibrary.isUnknown(creationRequest.getName()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var registered = animalLibrary.registerAnimal(creationRequest.getName(), creationRequest.getMaxAge(), creationRequest.getAdditionalInfo().getAggressive(), creationRequest.getAdditionalInfo().getVenomous(), creationRequest.getAdditionalInfo().getIndigenous(), creationRequest.getAdditionalInfo().getMystical());

        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoRegisterAnimalType201Response().createdId(registered.getId()).name(registered.getName()));
    }

    private DtoAnimalEntry mapToResp(AnimalEntity input) {
        return new DtoAnimalEntry().id(input.getId()).name(input.getName())
                .expectedMaxAge(input.getMaxAge())
                .ownershipLaw(evalRegistration(input))
                .ownershipLimits(evalHusbandry(input))
                .registered(map(input.getCreation()));
    }

    private DtoAnimalRegistrationType evalRegistration(AnimalEntity input) {
        if (input.isIndigenous() && !input.isMystical()) return DtoAnimalRegistrationType.PET;
        return DtoAnimalRegistrationType.EXOTIC;
    }

    private DtoHusbandryRuleType evalHusbandry(AnimalEntity input) {
        if (input.isMystical() && (input.isVenomous() || input.isAggressive())) return DtoHusbandryRuleType.FORBIDDEN;
        if ((input.isVenomous() && input.isAggressive())) return DtoHusbandryRuleType.PERMISSIONREQUIRED;
        if ((input.isVenomous() || input.isAggressive())) return DtoHusbandryRuleType.REGISTRATIONREQUIRED;
        return DtoHusbandryRuleType.UNLIMITED;
    }

    private OffsetDateTime map(Calendar calendar) {
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
        return instant.atOffset(zoneOffset);
    }
}
