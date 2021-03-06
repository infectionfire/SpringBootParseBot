package com.example.parser.VI;

import com.example.parser.interf.FeaturesFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.stream.IntStream;

/**
 * Формирование поля особенности для описания товарной карточки
 */

 class Advantages implements FeaturesFactory {//особенности

    private Advantages() {
        throw new IllegalStateException("Utility class");
    }


    static StringBuilder createAdvantagesVI(Document document) {
        StringBuilder advantagesCreator = new StringBuilder("<strong>Особенности:</strong>\n\n");
        Element element = document
                .select("div.advantages.spoiler")
                .first();
        if (element!=null) {
            Elements names = element.select("ul");//вытаскиваем инфу из маркированного списка
            Elements values = names.select("li");

            //цикл добавляет значения к строке, попутно форматируя ее
            IntStream.range(0, values.size()).forEach(i -> {
                String theme = values.get(i).select("li").text();
                if (values.size() - 1 != i) {
                    advantagesCreator.append("- ").append(theme).append(";\n");
                } else {
                    advantagesCreator.append("- ").append(theme).append(".\n\n");
                }
            });
        }
        return advantagesCreator;
    }
}