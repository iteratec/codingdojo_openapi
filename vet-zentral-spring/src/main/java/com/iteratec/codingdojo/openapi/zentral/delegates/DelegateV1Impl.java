package com.iteratec.codingdojo.openapi.zentral.delegates;

import com.iteratec.codingdojo.openapi.zentral.data.AnimalDao;
import com.iteratec.codingdojo.openapi.zentral.data.AnimalEntity;
import com.iteratec.codingdojo.openapi.zentral.generated.v1.api.AnimalsApiDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.UUID;

@Component
@Slf4j
public class DelegateV1Impl implements AnimalsApiDelegate {

    @Autowired AnimalDao animalsRepo;

    @Override public ResponseEntity<Void> animalsGet() {
        log.info("Loading animals");
        animalsRepo.findAll().forEach(animal -> log.info("Found animal: {}", animal.getName()));

        return ResponseEntity.ok().build();
    }

    @Override public ResponseEntity<Void> animalsPost() {
        var unknownAnimal = new AnimalEntity();
        unknownAnimal.setName(UUID.randomUUID().toString());
        var knownAnimal = animalsRepo.save(unknownAnimal);
        return ResponseEntity.created(URI.create("/animal/"+knownAnimal.getId())).build();
    }
}
