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
 */
@Component
public class ObjectToPayloadConverter {

    /**
     * Convert a given object to its JSON payload based on object's fields annotated as payload.
     *
     * @param object object to be converted to JSON payload; must be annotated as eligible for payload serialization.
     * @return a JSON string representing object's payload.
     * @throws PayloadSerializationException if object is null, or is not annotated as eligible for payload serialization.
     */
    public String convertToJsonPayload(Object object) throws PayloadSerializationException {
        try {
            validate(object);
            return getJsonPayloadString(object);
        } catch (Exception e) {
            throw new PayloadSerializationException(e.getMessage());
        }
    }

    /**
     * Check if conversion to JSON payload is possible.
     */
    private void validate(Object object) throws PayloadSerializationException {
        if (Objects.isNull(object)) {
            throw new PayloadSerializationException("The object to extract payload is null.");
        }

        Class<?> aClass = object.getClass();
        if (!aClass.isAnnotationPresent(PayloadSerializable.class)) {
            throw new PayloadSerializationException("Class " + aClass.getSimpleName() + " is not PayloadSerializable.");
        }
    }

    /**
     * Get a JSON payload string from object's fields annotated as payload.
     */
    private String getJsonPayloadString(Object object) throws Exception {
        Class<?> aClass = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : aClass.getDeclaredFields()) {
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
