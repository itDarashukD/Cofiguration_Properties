package com.example.brands.config;

import com.example.brands.entity.Brand;
import com.example.brands.entity.KFC;
import com.example.brands.entity.McDonald;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "timeranges")
public class BrandConfig {

 //   map str, col
    List<KFC> kfcList = new ArrayList<>();
    List<McDonald> mcDonaldList = new ArrayList<>();

    public <T> List<? extends Brand> getBrand(T t) {
        if (t instanceof KFC) {
            return kfcList;
        }
        if (t instanceof McDonald) {
            return mcDonaldList;
        }
        else return Collections.emptyList();
    }
}
