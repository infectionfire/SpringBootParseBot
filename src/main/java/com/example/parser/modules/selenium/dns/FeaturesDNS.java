package com.example.parser.modules.selenium.dns;

import com.example.parser.modules.interf.Features;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import static com.example.parser.methods.HtmlToText.html2text;

public class FeaturesDNS implements Features {

    public static List<String> createFeatures(Element document) {
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
