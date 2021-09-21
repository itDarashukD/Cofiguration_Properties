package com.example.cofiguration.config;

//import com.example.cofiguration.entity.Person;
import com.example.cofiguration.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "sample")
public class ConfigPropertiesSample {

    private String host;
    private int port;
    private String form;
    private List<String> streets;
    private Map<String, String> personCredential;
    private Person person;

}