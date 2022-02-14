package com.example.parser;

import com.example.parser.interf.Creator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class SeleniumDriverFactory implements Creator {


    private SeleniumDriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static ChromeDriver driverFactory(String url){
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        return driver;
    }
}