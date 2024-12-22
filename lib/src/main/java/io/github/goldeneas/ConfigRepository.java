package io.github.goldeneas;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.github.goldeneas.api.ConfigParser;
import io.github.goldeneas.api.ConfigWrapper;

public class ConfigRepository {
    private ConfigParser configParser = new ConfigParser();
    private Map<Class<?>, Object> configs = generateAllConfigurations();

    public void add(Class<?> clazz, Object instance) {
        this.configs.put(clazz, instance);
    }

    public <T> T get(Class<T> clazz) {
        return (T) this.configs.get(clazz);
    }

    public void saveAll() {
        List<ConfigWrapper> wrappers = configParser.encode(configs.values());
        wrappers.forEach((wrapper) -> {
            File file = new File(wrapper.getPath() + "/" + wrapper.getName());
            // TODO: Continue
        });
    }

    /*
     *  Finds all of the @Configurations and generates
     *  an instance for all of them
     */
    private Map<Class<?>, Object> generateAllConfigurations() {
        return ConfigUtil.getConfigurations()
            .stream()
            .collect(
                Collectors.toMap(
                    c -> c,
                    c -> ConfigUtil.createInstance(c)
                ));
    }
}
