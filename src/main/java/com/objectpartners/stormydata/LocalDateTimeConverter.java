package com.objectpartners.stormydata;

/**
 * Created by mikeh on 6/6/17.
 */
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
class LocalDateTimeConverter implements Converter<LocalDateTime, String> {


    @Override
    public String convert(LocalDateTime source) {
        if (source == null ){
            return null;
        }


        return source.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    }
}

