package com.example.cofiguration.service;

import com.example.cofiguration.config.ConfigPropertiesSample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final ConfigPropertiesSample config;

    @Autowired
    public PersonService(ConfigPropertiesSample config) {
        this.config = config;
    }

    public void getPerson() {
        System.out.println(config.getPerson().getAge());
        System.out.println(config.getPerson().getTelephone_number());
        System.out.println(config.getPerson().getName());
    }

    public void getAllData() {
        System.out.println(config.getForm());
        System.out.println(config.getHost());
        System.out.println(config.getPort());
        config.getStreets().forEach(System.out::println);
        config.getPersonCredential().forEach((x, y) -> System.out.println(x + " " + y));
    }
}
