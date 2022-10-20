# Vet Home - Animals Shelter Service

Backend Service provides APIs to other consumers while relying on a connection to vet-zentral.

Setup for the workshop:
* requires the url of the running zentral service in `application.properties` -> `zentral.api.url` 
* run `.\gradlew bootRunLocal` to start this service.
* implement `com.iteratec.codingdojo.openapi.shelter.delegates.AdoptionDelegateImpl.fetchAdoptedAnimals`

This service will reset the database on every restart so long as the property `spring.sql.init.mode` is given. Remove the property from the `application.properties`to keep the data.
