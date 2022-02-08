package com.example.parser.VI;

import com.example.parser.methods.HtmlToText;
import com.example.parser.interf.FeaturesFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 *Формирование начального текста описания товарной карточки
 */

 class Features implements FeaturesFactory {

    private Features() {
        throw new IllegalStateException("Utility class");
    }
    //доделать метод

    static StringBuilder createFeaturesVI(Document document) {

        Document page = document;
        Element element = page.select("div.content-block").first();
        if (element!=null){
            return new StringBuilder(HtmlToText.html2text(element.toString())+"\n\n");
        }
        return new StringBuilder("\n\n");
        }
}
