package com.example.parser.dns;

import com.example.parser.interf.FactoryCards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;

public class BuildCardDNS implements FactoryCards {

    static Logger log = LogManager.getLogger();

    private BuildCardDNS() {
        throw new IllegalStateException("Utility class");
    }


    public static String buildValidCard(ChromeDriver driver, String url) {
        String result = "";
        driver.get(url + "/characteristics/");//упрощение при получении ссылки на товар
        Document document;
        try {
            document = Jsoup.parse(driver.getPageSource());
            Element element = document
                    .select("div.product-card-tabs__contents")
                    .first();
            result = CharacteristicsDNS.TTXFactory(element);
        } finally {
            driver.quit();
            return result.length()<200? "Днс не создался":result;
        }
    }
}
