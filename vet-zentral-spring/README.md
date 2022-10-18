# Vet Zentral

Shared Backend Service provides APIs to other consumers.

requires JAVA 17 to be available to the gradle build task.

in the console from the root of this project run:
* `.\gradlew bootRunV1` to start this service with V1 support.
* `.\gradlew bootRunV2` to start this service with V2 support.
* `.\gradlew bootRunV1V2` to start this service with V1 & V2 support.

the service will create a file based DB on the first run, thanks to the property `spring.sql.init.mode` in
the `application.properties`. This needs to be disabled when the file based DB is kept between runs - otherwise there
will be an error on start up, where the init db script fails.
