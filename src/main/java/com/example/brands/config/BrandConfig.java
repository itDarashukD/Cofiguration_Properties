package com.example.brands.config;

import com.example.brands.entity.DayParts;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "time-ranges")
public class BrandConfig {

    Map<String, List<DayParts>> eating = new HashMap<>();
}
