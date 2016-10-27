package com.epam.as.bookparser.util;

import com.epam.as.bookparser.BookReaderTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Object for input initial properties.
 */
public class PropertyManager {
    public static PropertyManager propertyManager = new PropertyManager();
    private final Properties properties;
    private final Properties properties2;

    private PropertyManager() {
        properties = new Properties();
        try (InputStream in = BookReaderTest.class.getClassLoader().getResourceAsStream("parser.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties2 = new Properties();
        try (InputStream input = BookReaderTest.class.getClassLoader().getResourceAsStream("text.properties")) {
            properties2.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Properties getProperties() {
        return properties;
    }

    public Properties getProperties2() {
        return properties2;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);

    }

    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
