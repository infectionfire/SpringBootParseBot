package com.example.parser.modules.selenium;

import com.example.parser.modules.interf.Creator;
import com.example.parser.modules.selenium.dns.BuildCardDNS;
import com.example.parser.modules.selenium.eld.BuildCardEld;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.example.parser.modules.selenium.config.getUserAgent;


public class PageCreator implements Creator {

    @Rule
    public Timeout globalTimeout = new Timeout(300000);

    private PageCreator() {
        throw new IllegalStateException("Utility class");
    }

    public static String createDnsPageHTML(String url){
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        return BuildCardDNS.getDnsChars(driver, url);
    }

    public static String createEldPageHTML(String url) {
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        return BuildCardEld.getEldChars(driver, url);
    }
}