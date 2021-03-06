package com.example.parser.dns;

import com.example.parser.interf.CharacteristicsFactory;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.parser.methods.HtmlToText.html2text;

public class CharacteristicsDNS implements CharacteristicsFactory {

    protected CharacteristicsDNS() {
        throw new IllegalStateException("Utility class");

    }

    static List<String> createCharacteristics(Element element) {
        StringBuilder ttx = new StringBuilder("<strong>Технические характеристики</strong>\n\n");
        StringBuilder dimensions =
                new StringBuilder("<strong>Габаритные размеры</strong>\n\n- Габаритные размеры (ДхШхВ): ");
        List<String> result = new ArrayList<>();
        Elements name = element.select("div.product-characteristics__spec-title");
        Elements value = element.select("div.product-characteristics__spec-value");
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

    static List<String> createFeatures(Element document) {
        List<String> result = new ArrayList<>();
        if (document!=null) {
            Element page = document.select("p").first();
            if (page != null) {
                result = List.of((html2text(page.toString())).split("\\. "));
            }
        }
        return result;
    }

    static String TTXFactory(Element element){
        try{
            List<String> ttx = createCharacteristics(element);
            List<String> character = createFeatures(element);
            StringBuilder result = new StringBuilder(character.get(0)+".\n\n");
            result.append(ttx.get(0)).append("\n").append("<strong>Особенности:<strong>\n\n");
            IntStream.range(1, character.size()).forEach(i -> result.append("- ").append(character.get(i)).append(";\n"));
            result.replace(result.length()-2,result.length(),"\n\n");
            result.append("<strong>Комплектация:<strong>\n\n");
            result.append(ttx.get(1));
            BuildCardDNS.log.debug("Описание dns создано успешно");
            return result.toString();
        }catch (Exception e){
            BuildCardDNS.log.error("описание днс не сформировалось");
            return "";
        }
    }
}
