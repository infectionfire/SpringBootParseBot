package com.example.parser.methods;

import java.util.Optional;

import static com.example.parser.PageFactory.*;


/**
 * Файл для составления конфигурации описания
 */

public class StructureCardBuilder {

    protected StructureCardBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static String BuildDescription(String search) {

        String result = Optional.ofNullable(search)
                .filter(s -> s.length()>40)
                .orElseThrow(RuntimeException::new);

        if (search.contains("dns-shop")){
            return createDnsPageHTML(result);
        }else if(search.contains("eldorado")){
            return createEldPageHTML(result);
        } else if (search.contains("vseinstrumenti")){
            return createVIPageHTML(result);
        }

        return result;

    }
}
