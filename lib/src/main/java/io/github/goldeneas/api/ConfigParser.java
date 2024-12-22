package io.github.goldeneas.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.goldeneas.api.annotations.Configuration;

public class ConfigParser {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /*
     *  Takes a collection of classes with the @Configuration attribute.
     *  Returns a list of ConfigWrapper or null if processing failed.
     */
    public List<ConfigWrapper> encode(Collection<Object> instances) {
        return instances
            .stream()
            .map((instance) -> encode(instance))
            .collect(Collectors.toList());
    }

    /*
     *  Takes a class with the @Configuration attribute.
     *  Returns a ConfigWrapper or null if processing failed.
     */
    public ConfigWrapper encode(Object instance) {
        Class<?> clazz = instance.getClass();

        if (!clazz.isAnnotationPresent(Configuration.class)) {
            throw new RuntimeException("Class does not have an @Configuration");
        }

        Configuration config = clazz.getAnnotation(Configuration.class);
        String parsedJson = "";

        try {
            parsedJson = objectMapper.writeValueAsString(instance);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ConfigWrapper(config.name(),
                config.path(),
                parsedJson
            );
    }

    /*
     *  Takes a parsed json string and a class.
     *  Returns an instance of that class.
     */
    public <T> T decode(String parsedJson, Class<T> clazz) {
        T value = null;

        try {
            value = objectMapper.readValue(parsedJson, clazz);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }

        return value;
    }
}
