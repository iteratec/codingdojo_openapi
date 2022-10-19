package com.iteratec.codingdojo.openapi.shelter.connectors;

import com.iteratec.codingdojo.openapi.zentral.generated.api.DefaultApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class VetZentralConnectionConfig {

    @Bean
    DefaultApi vetZentralApi() {
        return new DefaultApi();
    }

}
