package io.github.goldeneas;

import java.util.List;

import dorkbox.annotation.AnnotationDefaults;
import dorkbox.annotation.AnnotationDetector;
import io.github.goldeneas.api.annotations.Configuration;

public class ConfigUtil {
    public static List<Class<?>> getConfigurations() {
        List<Class<?>> list = null;

        try {
            list = AnnotationDetector
                .scanClassPath()
                .forAnnotations(Configuration.class)
                .collect(AnnotationDefaults.getType);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

    public static Object createInstance(Class<?> clazz) {
        Object instance = null;

        try {
            instance = clazz
                .getConstructor()
                .newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return instance;
    }
}
