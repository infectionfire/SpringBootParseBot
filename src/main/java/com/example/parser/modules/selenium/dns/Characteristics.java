package com.example.parser.modules.selenium.dns;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static com.example.parser.methods.HtmlToText.html2text;

public class Characteristics {


    public static String build(Element element){
        try{
        List<String> ttx = createCharacteristicsDNS(element);
        List<String> character = createFeaturesDNS(element);
        StringBuilder result = new StringBuilder(character.get(0)+".\n\n");
        result.append(ttx.get(0)).append("\n").append("<strong>Особенности:<strong>\n\n");
        for (int i = 1;i<character.size();i++){
            result.append("- ").append(character.get(i)).append(";\n");
        }
        result.replace(result.length()-2,result.length(),"\n\n");
        result.append("<strong>Комплектация:<strong>\n\n");

        result.append(ttx.get(1));
        return result.toString();
        }catch (Exception e){
            return "";
        }
    }

    public static List<String> createFeaturesDNS(Element document) {
        List<String> result = new ArrayList<>();
        if (document!=null) {
            Element page = document.select("p").first();
            if (page != null) {
                result = List.of((html2text(page.toString())).split("\\. "));
            }
        }
        return result;
    }


    public static List<String> createCharacteristicsDNS(Element document) {
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
