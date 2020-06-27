package com.fairman.aidan.controllers;

import java.util.List;

import com.fairman.aidan.services.NumberReformattingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberController {

    @Autowired
    private NumberReformattingService numberReformatService;

    @GetMapping("/convert/{number}")
    public String get(@PathVariable String number, @RequestParam List<String> types) {
        if (types.size() < 1) {
            return "\"{Error\":\"Must Supply conversion type(s)\"}";
        } else {
            return  numberReformatService.formatNumber(number, types);
        }
    }
}