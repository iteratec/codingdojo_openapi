package com.iteratec.codingdojo.openapi.shelter.connectors;

import com.iteratec.codingdojo.openapi.zentral.generated.api.DefaultApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class VetZentralConnectionConfig {

    @Value("${zentral.api.url}")
    private String zentralUrl;

    @Bean
    DefaultApi vetZentralApi() {
        var api = new DefaultApi();
        api.getApiClient().setBasePath(zentralUrl);
        return api;
    }

}
