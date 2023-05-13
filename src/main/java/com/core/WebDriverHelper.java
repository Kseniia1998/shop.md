package com.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class WebDriverHelper {
    private static WebDriver currentDriver = null;

    public static WebDriver getChromeDriver() {
        if (currentDriver == null) {
            currentDriver =  WebDriverManager.chromedriver().create();
        }
        return currentDriver;
    }



}
