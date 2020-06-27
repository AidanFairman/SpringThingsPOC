package com.fairman.aidan.serializers;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.fairman.aidan.serializers.factory.NumberSerializerFactory;

@Configuration
@ComponentScan
public class SerializerConfig {
    @Bean
    public ServiceLocatorFactoryBean serializerFactoryBean(){
        ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
        bean.setServiceLocatorInterface(NumberSerializerFactory.class);
        return bean;
    }
}