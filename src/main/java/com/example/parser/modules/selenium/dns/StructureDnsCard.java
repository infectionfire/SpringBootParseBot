package com.example.parser.modules.selenium.dns;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;

public class StructureDnsCard {
    public static String getDnsChars(ChromeDriver driver, String url) {
        String result = "Введите валидную ссылку";
        driver.get(url + "/characteristics/");
        Document document;
        try {
            document = Jsoup.parse(driver.getPageSource());
            Element element = document
                    .select("div.product-card-tabs__contents")
                    .first();
            result = Characteristics.build(element);
        } finally {
            driver.quit();
            return result;
        }
    }
}