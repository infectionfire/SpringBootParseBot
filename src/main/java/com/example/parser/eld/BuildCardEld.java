package com.example.parser.eld;

import com.example.parser.interf.FactoryCards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BuildCardEld implements FactoryCards {

    private BuildCardEld() {
        throw new IllegalStateException("Utility class");
    }


    static Logger log = LogManager.getLogger();

     private static String EldFactory(Document document) {
        StringBuilder result = new StringBuilder();
        try{
            List<String> temp = FeaturesEld.createFeaturesEld(document);
            for (int i = 0; i < temp.size();i++){
                if (i < 2){
                    result.append(temp.get(i) + ".");
                }else if (i == 2){
                    result.append("\n\n"+ CharacteristicsEld.createCharacteristicsEld(document) + "\n<strong>Особенности</strong>\n\n");
                }else {
                    result.append("- " + temp.get(i) + ";\n");
                }
            }log.debug("Описание элд создано успешно");
        }catch (Exception e){
            log.error("Создание карты элд не удалось");
        }

        return result.replace(result.length()-2, result.length(),".\n")
                .append("\n<strong>Комплектация</strong>\n\n")
                .append("<strong>Габариты и вес</strong>\n\n- Габаритные размеры (ДхШхВ): ").toString();
    }

    public static String getEldChars(ChromeDriver driver, String url) {
        String result = "";
        driver.get(url);
        Document document;
        try {
            Thread.sleep(5000);
            document = Jsoup.parse(driver.getPageSource());
            result = EldFactory(document);
        } finally {
            driver.quit();
            return result.length()<200? "Элд не создался":result;
        }
    }
}