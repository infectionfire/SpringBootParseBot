package com.example.parser.VI;

import com.example.parser.interf.FactoryCards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.example.parser.VI.Advantages.createAdvantagesVI;
import static com.example.parser.VI.Characteristics.createCharacteristicsVI;
import static com.example.parser.VI.Equipment.createComplectationVI;
import static com.example.parser.VI.Features.createFeaturesVI;
import static com.example.parser.VI.Weight.createWeightVI;
import static com.example.parser.methods.StringCharacterFormatter.replaceSigns;

public class BuildCardVI  implements FactoryCards {
    static Logger log = LogManager.getLogger();

    public static String VIFactory(Document document) {

        StringBuilder oneProductCard = new StringBuilder();
        oneProductCard.append(createFeaturesVI(document))
                .append(createCharacteristicsVI(document))
                .append(createAdvantagesVI(document))
                .append(createComplectationVI(document))
                .append(createWeightVI(document));
        log.debug("Описание VI создано успешно");

        return replaceSigns(oneProductCard);
    }

    public static String buildVIChars(ChromeDriver driver, String url) {
        String result = "";
        driver.get(url);
        Document document;
        try {
            Thread.sleep(2000);
            document = Jsoup.parse(driver.getPageSource());
            result = VIFactory(document);
        } finally {
            driver.quit();
            return result.length()<200? "Ви не создался":result;
        }
    }
}
