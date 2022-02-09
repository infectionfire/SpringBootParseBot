package com.example.parser.VI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.net.URL;

import static com.example.parser.VI.Advantages.createAdvantagesVI;
import static com.example.parser.VI.Characteristics.createCharacteristicsVI;
import static com.example.parser.VI.Equipment.createComplectationVI;
import static com.example.parser.VI.Features.createFeaturesVI;
import static com.example.parser.VI.Weight.createWeightVI;
import static org.junit.jupiter.api.Assertions.*;

class BuildCardVITest {

    @Test
    void VIFactory() {
        Document document;
        StringBuilder oneProductCard = new StringBuilder();
        try {
            document = Jsoup.parse(new URL("https://www.vseinstrumenti.ru/instrument/svarochnoe-oborudovanie/mma/svarochnyj-apparat-invertor/resanta/invertornyj-svarochnyj-apparat-resanta-sai190k-65-36/"), 45000);
            oneProductCard.append(createFeaturesVI(document))
                    .append(createCharacteristicsVI(document))
                    .append(createAdvantagesVI(document))
                    .append(createComplectationVI(document))
                    .append(createWeightVI(document));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(oneProductCard!=null);
    }
}