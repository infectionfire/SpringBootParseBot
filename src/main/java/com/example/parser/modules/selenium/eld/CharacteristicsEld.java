package com.example.parser.modules.selenium.eld;

import com.example.parser.modules.Creator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicsEld  implements Creator {

    public static String createCharacteristicsEld(Document document) {

        Elements element = document
                .select("div.specificationTextTable.q-item-full-specs-table");
        StringBuilder ttx = new StringBuilder("<strong>Технические характеристики</strong>\n\n");

        List<String> temp = new ArrayList<>();
        Element name = element.select("table").first();
        Elements tt = name.select("td");
        for(Element elem: tt){
            if (!elem.toString().contains("colspan")){
                temp.add(elem.text());
            }
        }
        for (int i = 0; i<temp.size();i++){
            if (i%2==0){
                ttx.append("- ").append(temp.get(i)).append(": ");
            }else {
                ttx.append(temp.get(i)).append(";\n");
            }
        }
        ttx.replace(ttx.length()-2,ttx.length(),".\n");
        return ttx.toString().replaceAll(" Другие товары", "");
    }

    public static List<String> createFeaturesEld(Element document) {
        List<String> result = new ArrayList<>();
        Element page = document.select("div.innerContainer.goodDescriptionText.q-item-main-description.lazyload-block").first();
        if (page!=null){
            result = List.of(page.text().split("\\."));
        }
        return result;
    }

    public static String buildEld(Document document) {
        StringBuilder result = new StringBuilder();
        if (document!=null) {
            List<String> temp = createFeaturesEld(document);
            for (int i = 0;i<temp.size();i++){
                if (i<2){
                    result.append(temp.get(i)+".");
                }else if (i==2){
                    result.append("\n\n"+createCharacteristicsEld(document)+"\n<strong>Особенности</strong>\n\n");
                }else {
                    result.append("- "+temp.get(i)+";\n");
                }
            }
        }
        return result.replace(result.length()-2,result.length(),".\n")
                .append("\n<strong>Комплектация</strong>\n\n")
                .append("<strong>Габариты и вес</strong>\n\n- Габаритные размеры (ДхШхВ): ").toString();
    }

}
