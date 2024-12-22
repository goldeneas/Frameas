package io.github.goldeneas.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConfigWrapper {
    private String name;
    private String path;
    private String parsedJson;
}
