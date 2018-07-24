package ua.tax.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Mapper {
    private final static Map<String, Properties> props = new HashMap<>();

    public static void setProps(String key, Properties properties) {
        props.put(key, properties);
    }

    public static Properties getProperties(String key) {
        return props.get(key);
    }
}
