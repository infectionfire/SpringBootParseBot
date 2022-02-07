package com.example.parser.methods;

import com.example.parser.modules.selenium.dns.Characteristics;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;

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
            result = Characteristics.build(element);
        } finally {
            driver.quit();
            if (result.length()<200){
                return "Введите валидную ссылку";
            }
            return result;
        }
    }
}