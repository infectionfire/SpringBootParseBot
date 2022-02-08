package com.example.parser.modules.selenium.dns;

import com.example.parser.modules.interf.CharacteristicsFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.example.parser.methods.HtmlToText.html2text;

public class CharacteristicsDNS implements CharacteristicsFactory {

    private CharacteristicsDNS() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> createCharacteristics(Element document) {
        StringBuilder ttx = new StringBuilder("<strong>Технические характеристики</strong>\n\n");
        StringBuilder dimensions =
                new StringBuilder("<strong>Габаритные размеры</strong>\n\n- Габаритные размеры (ДхШхВ): ");
        List<String> result = new ArrayList<>();
        Elements name = document.select("div.product-characteristics__spec-title");
        Elements value = document.select("div.product-characteristics__spec-value");
        for (int i = 0; i < name.size(); i++) {
            String temp1 = html2text(name.get(i).toString());
            String temp2 = html2text(value.get(i).toString());
            if (!temp1.equals("Ширина") && !temp1.equals("Высота")
                    && !temp1.equals("Глубина")&& !temp1.equals("Толщина")) {
                ttx.append("- ").append(temp1).append(": ").append(temp2).append(";\n");
            } else {
                if (dimensions.toString().contains("см")){
                dimensions.append(temp2.strip().replace(" см", "")).append("x");}
                else if (dimensions.toString().contains("мм")){
                dimensions = new StringBuilder(dimensions.toString()
                        .replaceAll("мм", "")).append("мм.\n\n");
                }
            }
        }
        result.add(ttx.replace(ttx.length()-2,ttx.length(),".\n").toString());
        result.add("\n"+dimensions);

        return result;
    }
}
