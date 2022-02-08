package com.example.parser.modules.VI;

import com.example.parser.modules.interf.FactoryCards;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

import static com.example.parser.methods.StringCharacterFormatter.replaceSigns;
import static com.example.parser.modules.VI.Advantages.createAdvantagesVI;
import static com.example.parser.modules.VI.Characteristics.createCharacteristicsVI;
import static com.example.parser.modules.VI.Equipment.createComplectationVI;
import static com.example.parser.modules.VI.Features.createFeaturesVI;
import static com.example.parser.modules.VI.Weight.createWeightVI;

public class BuildCardVI  implements FactoryCards {
    static Logger log = LogManager.getLogger();

    public static String VIFactory(String search) {
        Document document;
        StringBuilder oneProductCard = new StringBuilder();
        try {
            document = Jsoup.parse(new URL(search), 45000);
            oneProductCard.append(createFeaturesVI(document))
                    .append(createCharacteristicsVI(document))
                    .append(createAdvantagesVI(document))
                    .append(createComplectationVI(document))
                    .append(createWeightVI(document));
            log.debug("Описание VI создано успешно");
        } catch (IOException e) {
            log.error("Создание карты VI не удалось");
            return "Введите валидную ссылку";
        }

        return replaceSigns(oneProductCard);
    }
}
