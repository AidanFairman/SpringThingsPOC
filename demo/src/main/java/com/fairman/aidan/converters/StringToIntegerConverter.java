package com.fairman.aidan.converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIntegerConverter implements Converter<String, Integer>{
    public Integer convert(String source){
        return Integer.valueOf(Integer.parseInt(source));
    }
}