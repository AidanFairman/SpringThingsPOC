package com.fairman.aidan.converters;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFloatConverter implements Converter<String, Float>{
    public Float convert(String source){
        return Float.valueOf(Float.parseFloat(source));
    }
}