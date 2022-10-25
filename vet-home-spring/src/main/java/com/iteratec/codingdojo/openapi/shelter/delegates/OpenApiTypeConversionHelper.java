package com.iteratec.codingdojo.openapi.shelter.delegates;

import org.springframework.http.HttpHeaders;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

public class OpenApiTypeConversionHelper {


    static OffsetDateTime map(Calendar calendar) {
        Date date = calendar.getTime();
        Instant instant = date.toInstant();
        ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
        return instant.atOffset(zoneOffset);
    }

    static Date map(LocalDate localDate) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    static LocalDate map(Date date) {
        return LocalDate.of(date.getYear() + 1900, date.getMonth(), date.getDate());
    }

    static Consumer<HttpHeaders> getHttpHeadersConsumer() {
        return (HttpHeaders httpHeaders) -> httpHeaders.add("Access-Control-Allow-Origin", "*");
    }

    private OpenApiTypeConversionHelper() {
    }
}
