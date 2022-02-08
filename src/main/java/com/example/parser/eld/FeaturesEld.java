package com.example.parser.eld;

import com.example.parser.interf.FeaturesFactory;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

 class FeaturesEld implements FeaturesFactory {

    private FeaturesEld() {
        throw new IllegalStateException("Utility class");
    }

    static List<String> createFeaturesEld(Element document) {
        List<String> result = new ArrayList<>();
        Element page = document.select("div.innerContainer.goodDescriptionText.q-item-main-description.lazyload-block").first();
        if (page != null){
            result = List.of(page.text().split("\\."));
        }
        return result;
    }
}
