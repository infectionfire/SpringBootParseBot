package com.example.parser.modules.selenium.eld;

import com.example.parser.modules.interf.Features;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class FeaturesEld implements Features {

    public static List<String> createFeaturesEld(Element document) {
        List<String> result = new ArrayList<>();
        Element page = document.select("div.innerContainer.goodDescriptionText.q-item-main-description.lazyload-block").first();
        if (page != null){
            result = List.of(page.text().split("\\."));
        }
        return result;
    }
}
