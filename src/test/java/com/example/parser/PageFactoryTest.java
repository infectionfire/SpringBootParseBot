package com.example.parser;

import com.example.parser.dns.BuildCardDNS;
import com.example.parser.eld.BuildCardEld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class PageFactoryTest {

    @Test
    void createDnsPageHTML() {
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        Assertions.assertTrue(BuildCardDNS.buildValidCard(driver, "https://www.dns-shop.ru/product/000014fd2260ed20/156-noutbuk-lenovo-ideapad-l340-15api-cernyj/characteristics/")!=null);
    }

    @Test
    void FailCreateDnsPageHTML() {
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        Assertions.assertTrue(BuildCardDNS.buildValidCard(driver, "https://www.dns-shop.ru/product/000").equals("Днс не создался"));
    }

    @Test
    void createEldPageHTML() {
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        Assertions.assertTrue(BuildCardEld.buildEldChars(driver, "https://www.eldorado.ru/cat/detail/vertikalnyy-pylesos-tefal-x-pert-3-60-versatile-handstick-ty6975wo/")!=null);
    }
    @Test

    void FailCreateEldPageHTML() {
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(config.getUserAgent());//подстановка юзерагента
        ChromeDriver driver = new ChromeDriver(options);
        Assertions.assertTrue(BuildCardEld.buildEldChars(driver, "https://www.eldorado.ru/c").equals("Элд не создался"));
    }
}