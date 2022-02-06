package com.example.parser.modules.selenium.dns;

import com.example.parser.modules.Creator;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import static com.example.parser.methods.HtmlToText.html2text;


/**
 *Формирование начального текста описания товарной карточки
 */

public class Features implements Creator {

    private Features() {
        throw new IllegalStateException("Utility class");
    }
    //доделать метод

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
}
