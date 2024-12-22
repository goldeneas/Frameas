package io.github.goldeneas;

import io.github.goldeneas.configs.TestConfig;

public class Frameas {
    public static void main(String[] args) {
        ConfigRepository repository = new ConfigRepository();

        System.out.println(repository.get(TestConfig.class)
            .getTest());
    }
}
