package com.dotcms.staticpublish;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads configuration values from config.properties file.
 */
public class PluginProperties {
    private static final String PROPERTY_FILE_NAME = "plugin.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            InputStream in = PluginProperties.class.getResourceAsStream("/" + PROPERTY_FILE_NAME);

            properties.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException : " + PROPERTY_FILE_NAME + " not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException : Can't read " + PROPERTY_FILE_NAME);
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String[] getPropertyArray(String key) {
        return properties.getProperty(key).split(",");
    }

    public static String getProperty(String key, String defaultValue) {
        String x = properties.getProperty(key);
        return (x == null) ? defaultValue : x;
    }
}