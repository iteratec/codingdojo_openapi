package com.iteratec.codingdojo.openapi.shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class VetHomeMain {

    public static void main(String[] args) {
        SpringApplication.run(VetHomeMain.class, args);
    }

}
