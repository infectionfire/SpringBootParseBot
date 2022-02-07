package com.example.parser.modules.selenium.dns;


import org.junit.Rule;
import org.junit.rules.Timeout;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.example.parser.modules.selenium.dns.config.getUserAgent;

public class pageCreator {

    @Rule
    public Timeout globalTimeout = new Timeout(300000);

    public static String createDnsPageHTML(String url){
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments(getUserAgent());
        ChromeDriver driver = new ChromeDriver(options);
        String result = StructureDnsCard.getDnsChars(driver, url);
        return result;
    }

}