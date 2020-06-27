package com.fairman.aidan.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLongConverter implements Converter<String, Long>{

    public Long convert(String source) {
        return Long.valueOf(Long.parseLong(source));
    }

}