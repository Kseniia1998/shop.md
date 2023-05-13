package com.core;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final String SYSTEM = "config.properties";

    public static String getConfigProperty(String property) {
        return getProperty(property);
    }

    private static String getProperty(String property) {
        InputStream resourcePath = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(PropertyReader.SYSTEM);
        System.out.println(resourcePath);
        Properties appProps = new Properties();
        try {
            appProps.load(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appProps.getProperty(property);
    }
}
