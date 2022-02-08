package com.example.parser.modules.selenium.dns;

import com.example.parser.modules.interf.FactoryCards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.IntStream;

import static com.example.parser.modules.selenium.dns.CharacteristicsDNS.createCharacteristics;

public class BuildCardDNS  implements FactoryCards {
    static Logger log = LogManager.getLogger();

    private BuildCardDNS() {
        throw new IllegalStateException("Utility class");
    }

    public static String DnsFactory(Element element){
        try{
        List<String> ttx = createCharacteristics(element);
        List<String> character = FeaturesDNS.createFeatures(element);
        StringBuilder result = new StringBuilder(character.get(0)+".\n\n");
        result.append(ttx.get(0)).append("\n").append("<strong>Особенности:<strong>\n\n");
        IntStream.range(1, character.size()).forEach(i -> result.append("- ").append(character.get(i)).append(";\n"));
        result.replace(result.length()-2,result.length(),"\n\n");
        result.append("<strong>Комплектация:<strong>\n\n");
        result.append(ttx.get(1));
        log.debug("Описание dns создано успешно");
        return result.toString();
        }catch (Exception e){
            log.error("Создание карты днс не удалось");
            return "";
        }
    }

    public static String getDnsChars(ChromeDriver driver, String url) {
        String result = "";
        driver.get(url + "/characteristics/");
        Document document;
        try {
            document = Jsoup.parse(driver.getPageSource());
            Element element = document
                    .select("div.product-card-tabs__contents")
                    .first();
            result = DnsFactory(element);
        } finally {
            driver.quit();
            return result.length()<200? "Введите валидную ссылку":result;
        }
    }
}
