package com.iteratec.codingdojo.openapi.shelter.connectors;

import com.iteratec.codingdojo.openapi.zentral.generated.api.DefaultApi;
import com.iteratec.codingdojo.openapi.zentral.generated.model.ExtAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
public class VetZentralConnector {

    @Autowired
    DefaultApi vetZentralApi;

    public Optional<ExtAnimal> findByName(@NotNull String name) {
        var list = vetZentralApi.animalsGet()
                .collectList()
                .blockOptional();
        if (list.isEmpty()) return Optional.empty();

        return list.get().stream()
                .filter(extAnimal -> extAnimal.getName().equals(name))
                .findFirst();
    }

}
