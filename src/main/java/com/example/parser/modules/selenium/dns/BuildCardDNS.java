package com.example.parser.modules.selenium.dns;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.IntStream;

import static com.example.parser.modules.selenium.dns.CharacteristicsDNS.createCharacteristics;

public class BuildCardDNS {
    static Logger log = LogManager.getLogger();

    public static String build(Element element){
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

}
