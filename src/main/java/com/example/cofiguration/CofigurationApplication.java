package com.example.cofiguration;

import com.example.cofiguration.config.ConfigPropertiesSample;
import com.example.cofiguration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CofigurationApplication {

    @Autowired
    private PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(CofigurationApplication.class, args);
    }

    @PostConstruct
    public void runAfterObjectCreated() {
        System.out.println("PostContruct method called");
        personService.getAllData();
        personService.getPerson();
    }
}
