package com.example.cofiguration;

import com.example.brands.entity.Brand;
import com.example.brands.entity.McDonald;
import com.example.brands.service.BrandServiceWithConf;
import com.example.cofiguration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@SpringBootApplication(scanBasePackages ={"com.example"})
public class CofigurationApplication {

    @Autowired
    private PersonService personService;

    @Autowired
    private BrandServiceWithConf brandService;

    public static void main(String[] args) {
        SpringApplication.run(CofigurationApplication.class, args);
    }

    @PostConstruct
    public void runAfterObjectCreated() {
        System.out.println("PostContruct method called");
        LocalTime userTimeToTest = createUserTimeToTest();
        Brand brand = new McDonald();
        brandService.getDailyPart(userTimeToTest,brand);
    }

    public  LocalTime createUserTimeToTest() {
        LocalTime userTime = LocalTime.of(0, 00);
        return userTime;
    }
}
