package com.example.parser.methods;

import com.example.parser.dns.BuildCardDNS;
import com.example.parser.eld.BuildCardEld;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Optional;

import static com.example.parser.SeleniumDriverFactory.driverFactory;
import static com.example.parser.VI.BuildCardVI.buildVIChars;


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

        ChromeDriver driver = driverFactory(search);

        if (search.contains("dns-shop")){
            return BuildCardDNS.buildValidCard(driver, search);
        }else if(search.contains("eldorado")){
            return BuildCardEld.buildEldChars(driver, search);
        } else if (search.contains("vseinstrumenti")){
            return buildVIChars(driver, search);
        }

        return result;

    }
}
