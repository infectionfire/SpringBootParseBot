package com.example.parser.dns;

import com.example.parser.interf.FeaturesFactory;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import static com.example.parser.methods.HtmlToText.html2text;

public class FeaturesDNS implements FeaturesFactory {

    protected FeaturesDNS() {
        throw new IllegalStateException("Utility class");
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
}
