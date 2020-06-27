package com.fairman.aidan.serializers;

import org.springframework.stereotype.Component;

@Component
public class Float implements NumberSerializer{
    public String serialize(Object f){
        return "{ \"float\": " + f.toString() + " }";
    }
}