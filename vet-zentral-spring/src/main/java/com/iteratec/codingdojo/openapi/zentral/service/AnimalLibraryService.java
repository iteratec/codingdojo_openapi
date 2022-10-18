package com.iteratec.codingdojo.openapi.zentral.service;

import com.iteratec.codingdojo.openapi.zentral.data.AnimalDao;
import com.iteratec.codingdojo.openapi.zentral.data.AnimalEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class AnimalLibraryService {

    @Autowired
    AnimalDao animalRepo;

    public List<String> fetchAnimalNames() {
        var list = animalRepo.findAll().iterator();
        return Stream.generate(() -> null)
                .takeWhile(x -> list.hasNext())
                .map(n -> list.next())
                .map(AnimalEntity::getName)
                .collect(Collectors.toList());
    }

    public Optional<AnimalEntity> fetchAnimal(@NotNull Integer id) {
        return animalRepo.findById(id);
    }

    public List<AnimalEntity> fetchAnimals() {
        var list = animalRepo.findAll().iterator();
        return Stream.generate(() -> null)
                .takeWhile(x -> list.hasNext())
                .map(n -> list.next())
                .collect(Collectors.toList());
    }

    public boolean isUnknown(String name) {
        return animalRepo.findFirstByName(name).isEmpty();
    }

    public AnimalEntity registerAnimal(String name, Integer maxAge, boolean aggressive, boolean venomous, boolean indigenous, boolean mystical) {
        var mapped = AnimalEntity.builder()
                .name(name)
                .maxAge(maxAge)
                .aggressive(aggressive)
                .venomous(venomous)
                .indigenous(indigenous)
                .mystical(mystical)
                .build();
        log.info("Store animal [{}]", mapped);
        return animalRepo.save(mapped);
    }
}
