package com.example.parser.VI;

import com.example.parser.interf.FeaturesFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Парсит страницу и берет ссылку на инструкцию если она есть
 * в случае отсутствия инструкции в формате PDF возвращает пустую строку для заполнения ячейки в эксель
 */

 class ManualCrawler implements FeaturesFactory {
    private static String instr = "";


     static String getManual(Document document) {
        CreateInstrUrlVI(document);
        return instr;
    }

    static String CreateInstrUrlVI(Document document) {
        StringBuilder setInstr = new StringBuilder("");
        Document page = document;
        Element imageElement = page.select("ul.unordered-list.-links.spoiler.-download").first();
        if (imageElement != null) {
            instr = imageElement.toString();
            String[] ins = instr.split("<a href=\"//");
            ins = ins[1].split("\"");
            setInstr= new StringBuilder("https://"+ins[0]);
            if (!setInstr.toString().endsWith(".pdf")) {
                setInstr = new StringBuilder("");
            }
        }
        instr=setInstr.toString();

        return instr;
    }
}





