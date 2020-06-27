package com.fairman.aidan.services;

import java.util.List;

import com.fairman.aidan.serializers.factory.NumberSerializerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class NumberReformattingService {
    @Autowired
    private NumberSerializerFactory serializers;

    @Autowired
    private ConversionService converters;

    public String formatNumber(String number, List<String> types) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"Numbers\": [");
        for (String t : types) {
            switch (t) {
                case "long":
                    serialize(number, t, Long.class, sb);
                    break;
                case "integer":
                    serialize(number, t, Integer.class, sb);
                    break;
                case "float":
                    serialize(number, t, Float.class, sb);
                    break;
                case "double":
                    serialize(number, t, Double.class, sb);
                    break;
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    private void serialize(String number, String type, Class c, StringBuilder sb) {
        try {
            Object o = converters.convert(number, c);
            sb.append((needsComma(sb)) ? ", " : "").append(serializers.getSerializer(type).serialize(o));
        } catch (ConversionFailedException ex) {
            sb.append((needsComma(sb)) ? ", " : "").append("{\"").append(type).append("\":\"Conversion failed!\"}");
        }
    }

    private boolean needsComma(StringBuilder sb) {
        String s = sb.toString();
        if (s.charAt(s.length() - 1) == '[') {
            return false;
        }
        return true;
    }

}