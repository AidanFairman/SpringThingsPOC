package com.fairman.aidan.converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDoubleConverter implements Converter<String, Double>{
    public Double convert(String source){
        return Double.valueOf(Double.parseDouble(source));
    }
}