package com.example.parser.methods;

import static com.example.parser.modules.VI.BuildCardVI.buildVI;
import static com.example.parser.modules.selenium.PageCreator.createDnsPageHTML;
import static com.example.parser.modules.selenium.PageCreator.createEldPageHTML;


/**
 * Файл для составления конфигурации описания
 */
public class StructureCardBuilder {

    private StructureCardBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static String BuildDescriptionVI(String search) {

        if (search.contains("dns-shop")){
            return createDnsPageHTML(search);
        }else if(search.contains("eldorado")){
            return createEldPageHTML(search);
        } else if (search.contains("vseinstrumenti")){
            return buildVI(search);
        }else {
            return "";
        }
    }
}
