package com.example.brands.service;

import com.example.brands.config.BrandConfig;
import com.example.brands.entity.DayParts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BrandServiceWithConf {

    @Autowired
    private BrandConfig config;

    public void printObjects() {
//        List<KFC> kfcList = config.getKfcList();
//        System.out.println(kfcList);
//        kfcList.forEach(System.out::println);
        Map<String, List<DayParts>> eating = config.getEating();
        System.out.println(eating);
        List<DayParts> dayParts = eating.get("kfc");
        dayParts.forEach(x ->System.out.println(x.getName()));
    }

    public void getDailyPart(LocalTime time, String anyBrand) {

        List<DayParts> dayParts = config.getEating().get(anyBrand);
        List<DayParts> collect = dayParts.stream()
                .filter(dayPart -> LocalTime.parse(dayPart.getTimeBegin()).isBefore(time)||
                        LocalTime.parse(dayPart.getTimeBegin()).equals(time) )
                .filter(dayPart -> LocalTime.parse(dayPart.getTimeFinish()).isAfter(time))
                .collect(Collectors.toList());

        collect.forEach(x -> System.out.println(x.getName()));

        if (collect.isEmpty()) {
            DayParts dayPartNextEating = dayParts.stream()
                    .filter(dayPart -> LocalTime.parse(dayPart.getTimeBegin()).isAfter(time))
                    .collect(Collectors.toList())
                    .stream()
                    .findFirst()
                    .get();

            System.out.println("The next eating is a " + dayPartNextEating.getName() + " at :" + dayPartNextEating.getTimeBegin());
        }
    }
}

