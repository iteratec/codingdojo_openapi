package com.iteratec.codingdojo.openapi.zentral.delegates;

import com.iteratec.codingdojo.openapi.zentral.data.AnimalEntity;
import com.iteratec.codingdojo.openapi.zentral.generated.v1.api.AnimalsApiDelegate;
import com.iteratec.codingdojo.openapi.zentral.generated.v1.model.DtoAnimal;
import com.iteratec.codingdojo.openapi.zentral.generated.v1.model.DtoRegisterAnimal;
import com.iteratec.codingdojo.openapi.zentral.service.AnimalLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("V1")
@Slf4j
@Validated
public class DelegateV1Impl implements AnimalsApiDelegate {

    @Autowired
    AnimalLibraryService animalLibrary;

    @Override
    public ResponseEntity<DtoAnimal> animalsIdGet(String id) {
        var result = animalLibrary.fetchAnimal(Integer.valueOf(id));
        return result
                .map(animal -> ResponseEntity.ok(mapToPayload(animal)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<DtoAnimal>> animalsGet() {
        log.info("Loading animals");
        var found = animalLibrary.fetchAnimals().stream()
                .map(this::mapToPayload).collect(Collectors.toList());
        return ResponseEntity.ok(found);
    }

    @Override
    public ResponseEntity<Void> animalsPost(DtoRegisterAnimal creationObj) {
        if (!animalLibrary.isUnknown(creationObj.getName())) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        var created = animalLibrary.registerAnimal(creationObj.getName(), creationObj.getMaxAge(), creationObj.getAdditionalInfo().getAggressive(), creationObj.getAdditionalInfo().getVenomous(), creationObj.getAdditionalInfo().getIndigenous(), creationObj.getAdditionalInfo().getMystical());
        return ResponseEntity.created(URI.create("/animals/" + created.getId())).build();
    }

    private DtoAnimal mapToPayload(AnimalEntity animal) {
        return new DtoAnimal().id(animal.getId()).name(animal.getName()).maxAge(animal.getMaxAge())
                .pet(animal.isIndigenous() && !animal.isVenomous());
    }
}
