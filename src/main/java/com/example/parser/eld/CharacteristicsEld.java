package com.example.parser.eld;

import com.example.parser.methods.HtmlToText;
import com.example.parser.interf.CharacteristicsFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

 class CharacteristicsEld implements CharacteristicsFactory {

    private CharacteristicsEld() {
        throw new IllegalStateException("Utility class");
    }

     static String createCharacteristicsEld(Document document) {

        Elements element = document
                .select("div.specificationTextTable.q-item-full-specs-table");
        StringBuilder ttx = new StringBuilder("<strong>Технические характеристики</strong>\n\n");

        List<String> temp = new ArrayList<>();
        Element name = element.select("table").first();
        Elements tt = name.select("td");

        tt.stream().filter(elem -> !elem.toString().contains("colspan")).forEach(elem -> {
            if (!elem.toString().contains("</div>")) {
                temp.add(elem.text());
            } else {
                String[] arr = elem.toString().split("<div");
                temp.add(HtmlToText.html2text(arr[0]));
            }
        });

        IntStream.range(0, temp.size()).forEach(i -> {
            if (i % 2 == 0) {
                ttx.append("- ").append(temp.get(i)).append(": ");
            } else {
                ttx.append(temp.get(i)).append(";\n");
            }
        });

        ttx.replace(ttx.length()-2,ttx.length(),".\n");
        return ttx.toString().replaceAll(" Другие товары", "");
    }

     static List<String> createFeaturesEld(Element document) {
         List<String> result = new ArrayList<>();
         Element page = document.select("div.innerContainer.goodDescriptionText.q-item-main-description.lazyload-block").first();
         if (page != null){
             result = List.of(page.text().split("\\."));
         }
         return result;
     }

     static String TTXFactory(Document document) {
        StringBuilder result = new StringBuilder();
        try{
            List<String> temp = createFeaturesEld(document);
            for (int i = 0; i < temp.size();i++){
                if (i < 2){
                    result.append(temp.get(i) + ".");
                }else if (i == 2){
                    result.append("\n\n"+ createCharacteristicsEld(document) + "\n<strong>Особенности</strong>\n\n");
                }else {
                    result.append("- " + temp.get(i) + ";\n");
                }
            }
            BuildCardEld.log.debug("Описание элд создано успешно");
        }catch (Exception e){
            BuildCardEld.log.error("Создание карты элд не удалось");
        }

        return result.replace(result.length()-2, result.length(),".\n")
                .append("\n<strong>Комплектация</strong>\n\n")
                .append("<strong>Габариты и вес</strong>\n\n- Габаритные размеры (ДхШхВ): ").toString();
    }
 }
