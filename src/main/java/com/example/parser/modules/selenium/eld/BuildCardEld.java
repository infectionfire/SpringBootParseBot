package com.example.parser.modules.selenium.eld;

import com.example.parser.modules.interf.Creator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.List;

public class BuildCardEld implements Creator {

    static Logger log = LogManager.getLogger();

    public static String buildEld(Document document) {
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

}
