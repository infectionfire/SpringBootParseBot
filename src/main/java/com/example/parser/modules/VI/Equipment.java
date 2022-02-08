package com.example.parser.modules.VI;

import com.example.parser.modules.interf.Creator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Формирование поля "Комплектация" для описания товарной карточки
 */

public class Equipment  {

    private Equipment() {
        throw new IllegalStateException("Utility class");
    }

    public static StringBuilder createComplectationVI(Document document) {
        StringBuilder equipment = new StringBuilder("<strong>Комплектация:</strong>\n\n");
        Document page = document;

        Element tableParameter = page.select("div.equipment.spoiler").first();
        if (tableParameter!=null){
            Elements names = tableParameter.select("ul");
            Elements values = names.select("li");
            StringBuilder finalEquipment = equipment;
            values.forEach(value -> {
                finalEquipment.append("- " + value.select("li").text() + ";\n");
            });
            equipment = new StringBuilder(finalEquipment.toString().replace(";;", ";"));
            equipment.replace(equipment.length() - 2, equipment.length() - 1, ".")
                    .append("\n");
            return new StringBuilder(equipment.toString()
                    .replace("..", "."));
        }
        return equipment;

    }
}