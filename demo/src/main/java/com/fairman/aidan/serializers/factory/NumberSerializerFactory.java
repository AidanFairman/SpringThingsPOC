package com.fairman.aidan.serializers.factory;

import com.fairman.aidan.serializers.NumberSerializer;

public interface NumberSerializerFactory{
    NumberSerializer getSerializer(String numtype);
}