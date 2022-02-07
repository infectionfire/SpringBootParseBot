package com.example.parser.methods;

import com.example.parser.modules.selenium.dns.CharacteristicsDNS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.example.parser.modules.selenium.eld.CharacteristicsEld.buildEld;

public class SeleniumBuilder {

    public static String getDnsChars(ChromeDriver driver, String url) {
        String result = "";
        driver.get(url + "/characteristics/");
        Document document;
        try {
            document = Jsoup.parse(driver.getPageSource());
            Element element = document
                    .select("div.product-card-tabs__contents")
                    .first();
            result = CharacteristicsDNS.build(element);
        } finally {
            driver.quit();
            return result.length()<200? "Введите валидную ссылку":result;
        }
    }

    public static String getEldChars(ChromeDriver driver, String url) {
        String result = "";
        driver.get(url);
        Document document;
        try {
            Thread.sleep(5000);
            document = Jsoup.parse(driver.getPageSource());
            result = buildEld(document);
        } finally {
            driver.quit();
            return result.length()<200? "Введите валидную ссылку":result;
        }
    }
}