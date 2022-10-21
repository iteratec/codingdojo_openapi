package com.iteratec.codingdojo.openapi.shelter.delegates;

import java.time.*;
import java.util.Calendar;
import java.util.Date;

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

    private OpenApiTypeConversionHelper() {
    }
}
