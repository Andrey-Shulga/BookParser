package com.epam.as.bookparser.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Object for input initial properties.
 */
public class PropertyManager {

    private Properties properties;

    public PropertyManager(String propertyFileName) {
        properties = new Properties();
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
