package com.example.cofiguration;


import com.example.brands.entity.DayParts;
import com.example.brands.service.BrandServiceWithConf;
import com.example.cofiguration.service.PersonService;
import com.example.conversationService.samples.StringToCharArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class CofigurationApplication {

    @Autowired
    private BrandServiceWithConf brandService;

    public static void main(String[] args) {
        SpringApplication.run(CofigurationApplication.class, args);
    }

//   @PostConstruct                                 //comments for testing
    public void runAfterObjectCreated() {
        System.out.println("PostContruct method called");
        LocalTime userTimeToTest = createUserTimeToTest();
        List<DayParts> mcDonald = brandService.getDailyPart(userTimeToTest, "mcDonald");
    }

    public LocalTime createUserTimeToTest() {
        LocalTime userTime = LocalTime.of(21, 00);
        return userTime;
    }
}
