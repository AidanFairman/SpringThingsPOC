package com.fairman.aidan.serializers;

import org.springframework.stereotype.Component;

@Component
public class Double implements NumberSerializer{
    public String serialize(Object d){
        return "{ \"double\": " + d.toString() + " }";
    }
}