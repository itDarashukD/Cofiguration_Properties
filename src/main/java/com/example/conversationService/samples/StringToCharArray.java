package com.example.conversationService.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class StringToCharArray {

    @Autowired
    private SupportConvert supportConvert;

    public DefaultConversionService defaultConversionService = new DefaultConversionService();

    public void lerningConvrtors() throws Exception {
        List<Integer> integerList = List.of(1, 2, 3);
        List<String> stringList = supportConvert.convertCollection(integerList, String.class);
        System.out.println(stringList.toString());

        Map<String, String> first = new HashMap<>();
        first.put("1111", "222");
        first.put("3333", "4444");
        Map<Integer, Integer> second = new HashMap<>();
        second.put(1111, 222);
        second.put(3333, 4444);
        Map<Integer, Integer> integerIntegerMap = supportConvert.convertMap(first, Integer.class, Integer.class);

        Map<String , String > stringMap = supportConvert.convertMap(second, String .class, String .class);
        stringMap.forEach((x, y) -> System.out.println(x + " " + y.concat("www")));
        System.out.println(supportConvert.convert(integerList, String.class));

        List<Character> characterList = List.of('q', 'e', '3');
        List<String> convert2 = (List<String>) defaultConversionService.convert(characterList
                , TypeDescriptor.forObject(characterList)
                , TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class)));

        List<String> s = List.of("w", "e", "e");
        List<Character> convert3 = (List<Character>) defaultConversionService.convert(s
                , TypeDescriptor.forObject(s)
                , TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Character.class)));

        String[] si = new String[]{"2", "4", "6", "4"};
        List<Character> convert4 = (List<Character>) defaultConversionService.convert(si
                , TypeDescriptor.forObject(si)
                , TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Character.class)));

        Map<String, String> first3 = new HashMap<>();
        first.put("1111", "222");
        first.put("3333", "4444");
        Map<Integer, Integer> convert5 = (Map<Integer, Integer>) defaultConversionService.convert(first3
                , TypeDescriptor.map(Map.class,
                        TypeDescriptor.valueOf(Integer.class),
                        TypeDescriptor.valueOf(Integer.class)));
    }


}
