package com.example.brands.service;

import com.example.brands.config.BrandConfig;
import com.example.brands.entity.DayParts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceWithConf {

    @Autowired
    private BrandConfig config;

    public List<DayParts> getDailyPart(LocalTime time, String anyBrand) {
        List<DayParts> dailyPartsList = getListOfDailyParts(time, anyBrand);

        if (dailyPartsList.isEmpty()) {
            return List.of(getNextDailyPart(time, anyBrand));
        }
        return dailyPartsList;
    }

    public List<DayParts> getListOfDailyParts(LocalTime time, String anyBrand) {
        List<DayParts> dayParts = config.getEating().get(anyBrand);
        return dayParts.stream()
                .filter(dayPart -> LocalTime.parse(dayPart.getTimeBegin()).isBefore(time) ||
                        LocalTime.parse(dayPart.getTimeBegin()).equals(time))
                .filter(dayPart -> LocalTime.parse(dayPart.getTimeFinish()).isAfter(time))
                .collect(Collectors.toList());
    }

    public DayParts getNextDailyPart(LocalTime time, String anyBrand) {
        List<DayParts> dayParts = config.getEating().get(anyBrand);
        return dayParts.stream()
                .filter(dayPart -> LocalTime.parse(dayPart.getTimeBegin()).isAfter(time))
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .get();
    }
}

