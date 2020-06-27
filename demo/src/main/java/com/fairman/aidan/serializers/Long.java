package com.fairman.aidan.serializers;

import org.springframework.stereotype.Component;

@Component
public class Long implements NumberSerializer{
    public String serialize(Object l){
        return "{ \"long\": " + l.toString() + " }";
    }
}