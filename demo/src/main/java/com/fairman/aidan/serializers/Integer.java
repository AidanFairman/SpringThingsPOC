package com.fairman.aidan.serializers;

import org.springframework.stereotype.Component;

@Component
public class Integer implements NumberSerializer{
    public String serialize(Object i){
        return "{ \"integer\": " + i.toString() + " }";
    }
}