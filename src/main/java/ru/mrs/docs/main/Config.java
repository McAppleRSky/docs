package ru.mrs.docs.main;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private final Map<String, String> properties = new HashMap();

    public Config(String property1, String value1) {
        setProperty(property1, value1);
    }

    public String getProperty(String property) {
        return properties.get(property);
    }

    public void setProperty(String property, String value) {
        properties.put(property, value);
    }

}
