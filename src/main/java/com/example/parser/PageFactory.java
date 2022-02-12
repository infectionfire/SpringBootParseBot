package com.example.parser;

import com.example.parser.dns.BuildCardDNS;
import com.example.parser.eld.BuildCardEld;
import com.example.parser.interf.Creator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.example.parser.VI.BuildCardVI.buildVIChars;


public class PageFactory implements Creator {


    private PageFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static String createDnsPageHTML(String url){
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        return BuildCardDNS.buildValidCard(driver, url);
    }

    public static String createEldPageHTML(String url) {
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        return BuildCardEld.buildEldChars(driver, url);
    }

    public static String createVIPageHTML(String url){
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        return buildVIChars(driver, url);
    }
}