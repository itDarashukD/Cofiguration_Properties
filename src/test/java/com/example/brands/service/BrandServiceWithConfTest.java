package com.example.brands.service;

import com.example.brands.config.BrandConfig;
import com.example.brands.entity.DayParts;
import com.example.cofiguration.CofigurationApplication;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = {CofigurationApplication.class})
class BrandServiceWithConfTest {

    @MockBean
    private BrandConfig config;

    @Autowired
    private BrandServiceWithConf service;

    private static DayParts dayPart1;
    private static DayParts dayPart2;
    private static DayParts dayPart3;
    private static DayParts dayPart4;
    private static DayParts dayPart5;
    private static DayParts dayPart6;
    private static DayParts dayPart7;

    private static List<DayParts> withoutAnyPart;

    private static LocalTime time1;
    private static LocalTime time2;
    private static LocalTime time3;
    private static LocalTime time4;
    private static LocalTime time5;
    private static LocalTime time6;
    private static LocalTime time7;

    private Map<String, List<DayParts>> eating = new HashMap<>();

    public static void prepareData() {
        dayPart1 = new DayParts("first", "08:00", "09:00");
        dayPart2 = new DayParts("second", "10:00", "11:00");
        dayPart3 = new DayParts("third", "12:00", "13:00");
        dayPart4 = new DayParts("fourth", "15:00", "16:00");
        dayPart5 = new DayParts("fifth", "17:00", "18:30");
        dayPart6 = new DayParts("fifth", "19:00", "19:50");
        dayPart7 = new DayParts("fifth", "21:00", "23:59");

        withoutAnyPart = new ArrayList<>();

        time1 = LocalTime.of(8, 15);
        time2 = LocalTime.of(11, 30);
        time3 = LocalTime.of(12, 30);
        time4 = LocalTime.of(13, 30);
        time5 = LocalTime.of(17, 00);
        time6 = LocalTime.of(6, 00);
        time7 = LocalTime.of(0, 00);
    }

    public static Stream<Arguments> prepareDataForgetListOfDailyParts() {
        prepareData();
        return Stream.of(
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time1, List.of(dayPart1)),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time2, withoutAnyPart),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time3, List.of(dayPart3)),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time4, withoutAnyPart),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time5, List.of(dayPart5)),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time6, withoutAnyPart)
        );
    }

    public static Stream<Arguments> prepareDataForgetNextDailyPart() {
        prepareData();
        return Stream.of(
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time2, dayPart3),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time4, dayPart4),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time6, dayPart1),
                Arguments.of(List.of(dayPart1, dayPart2, dayPart3, dayPart4, dayPart5, dayPart6, dayPart7), time7, dayPart1)
        );
    }

    @ParameterizedTest
    @MethodSource("prepareDataForgetListOfDailyParts")
    void getListOfDailyParts(List<DayParts> input, LocalTime time, List<DayParts> output) {
        eating.put("testBrand", input);
        when(config.getEating()).thenReturn(eating);
        List<DayParts> expected = service.getListOfDailyParts(time, "testBrand");
        assertEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("prepareDataForgetNextDailyPart")
    void getNextDailyPart(List<DayParts> input, LocalTime time, DayParts output) {
        eating.put("testBrand", input);
        when(config.getEating()).thenReturn(eating);
        DayParts expected = service.getNextDailyPart(time, "testBrand");
        assertEquals(expected, output);
    }
}