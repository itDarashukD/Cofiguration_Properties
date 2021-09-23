package com.example.brands.service;

import com.example.brands.config.BrandConfig;
import com.example.brands.entity.Brand;
import com.example.brands.entity.KFC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceWithConf {

    @Autowired
    private BrandConfig config;

    public void printObjects() {
        List<KFC> kfcList = config.getKfcList();
        System.out.println(kfcList);
        kfcList.forEach(System.out::println);
    }

    public void getDailyPart(LocalTime time,Brand anyBrand) {
        List<Brand> brandList = (List<Brand>) config.getBrand(anyBrand);
        List<Brand> collect = brandList.stream()
                .filter(brand -> LocalTime.parse(brand.getTimeBegin()).isBefore(time)||
                        LocalTime.parse(brand.getTimeBegin()).equals(time) )
                .filter(brand -> LocalTime.parse(brand.getTimeFinish()).isAfter(time)||
                        LocalTime.parse(brand.getTimeFinish()).equals(time))
                .collect(Collectors.toList());

        collect.forEach(x -> System.out.println(x.getName()));

        if (collect.isEmpty()) {
            Brand brandNextEating = brandList.stream()
                    .filter(brand -> LocalTime.parse(brand.getTimeBegin()).isAfter(time))
                    .collect(Collectors.toList())
                    .stream()
                    .sorted(Comparator.comparing(Brand::getTimeBegin))
                    .collect(Collectors.toList())
                    .stream()
                    .findFirst()
                    .get();

            System.out.println("The next eating is a " + brandNextEating.getName() + " at :" + brandNextEating.getTimeBegin());
        }
    }
}

