package com.example.parser.eld;

import com.example.parser.interf.FactoryCards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;

public class BuildCardEld implements FactoryCards {

    private BuildCardEld() {
        throw new IllegalStateException("Utility class");
    }


    static Logger log = LogManager.getLogger();

    public static String buildEldChars(ChromeDriver driver, String url) {
        String result = "";
        driver.get(url);
        Document document;
        try {
            Thread.sleep(5000);
            document = Jsoup.parse(driver.getPageSource());
            result = CharacteristicsEld.TTXFactory(document);
        } finally {
            driver.quit();
            return result.length()<200? "Элд не создался":result;
        }
    }
}
