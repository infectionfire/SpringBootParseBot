package com.example.parser.methods;

import static com.example.parser.PageFactory.*;


/**
 * Файл для составления конфигурации описания
 */

public class StructureCardBuilder {

    protected StructureCardBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static String BuildDescriptionVI(String search) {

        if (search.contains("dns-shop")){
            return createDnsPageHTML(search);
        }else if(search.contains("eldorado")){
            return createEldPageHTML(search);
        } else if (search.contains("vseinstrumenti")){
            return createVIPageHTML(search);
        }

        return "Введите валидную ссылку";

    }
}
