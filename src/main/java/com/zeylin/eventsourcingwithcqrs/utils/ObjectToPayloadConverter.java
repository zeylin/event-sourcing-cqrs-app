package com.zeylin.eventsourcingwithcqrs.utils;

import com.zeylin.eventsourcingwithcqrs.exceptions.PayloadSerializationException;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.PayloadElement;
import com.zeylin.eventsourcingwithcqrs.utils.annotations.PayloadSerializable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Convert object to a JSON string payload.
 *
 * Reference: Creating a Custom Annotation in Java, Baeldung.
 * Link: baeldung dot com slash java-custom-annotation
 */
@Component
public class ObjectToPayloadConverter {

    public String convertToJsonPayload(Object object) throws PayloadSerializationException {
        try {
            checkIfSerializable(object);
            return getJsonString(object);
        } catch (Exception e) {
            throw new PayloadSerializationException(e.getMessage());
        }
    }

    private void checkIfSerializable(Object object) throws PayloadSerializationException {
        if (Objects.isNull(object)) {
            throw new PayloadSerializationException("The object to extract payload is null.");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(PayloadSerializable.class)) {
            throw new PayloadSerializationException("Class " + clazz.getSimpleName() + " is not PayloadSerializable.");
        }
    }

    private String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(PayloadElement.class)) {
                jsonElementsMap.put(getKey(field), field.get(object).toString());
            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field) {
        String value = field.getAnnotation(PayloadElement.class).key();
        return value.isEmpty() ? field.getName() : value;
    }

}
