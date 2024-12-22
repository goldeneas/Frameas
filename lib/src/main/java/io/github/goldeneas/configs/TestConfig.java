package io.github.goldeneas.configs;

import io.github.goldeneas.api.annotations.Configuration;
import lombok.Getter;

@Configuration(name = "testConfig.json")
@Getter
public class TestConfig {
    private String test = "hello world!";
    private int a = 2;
    private float b = 2.2f;
}
