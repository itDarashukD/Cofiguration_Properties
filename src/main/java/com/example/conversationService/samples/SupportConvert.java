package com.example.conversationService.samples;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SupportConvert extends DefaultConversionService {

    public DefaultConversionService defaultConversionService = new DefaultConversionService();


    public <T> List<T> convertCollection(Object source, Class<T> targetType) {

        return (List<T>) defaultConversionService.convert(source,
                TypeDescriptor.forObject(source),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(targetType)));
    }

    public <T, S> Map<T, T> convertMap(Map<S, S> map, Class<T> var1, Class<T> var2) {

        return (Map<T, T>) defaultConversionService.convert(map
                , TypeDescriptor.map(Map.class,
                        TypeDescriptor.valueOf(var1),
                        TypeDescriptor.valueOf(var2)));

    }
}

