package org.example.db;

import lombok.Data;

import java.io.IOException;
import java.util.Properties;

@Data
public class Configuration {

    private final static Properties APPLICATION_PROPERTIES = new Properties();

    static{
        try {
            APPLICATION_PROPERTIES.load(Configuration.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){
        return APPLICATION_PROPERTIES.getProperty(key);
    }
}