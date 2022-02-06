package com.example.parser.modules.selenium.dns;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static com.example.parser.modules.selenium.dns.Characteristics.createCharacteristicsDNS;
import static com.example.parser.modules.selenium.dns.Features.createFeaturesDNS;

public class pageCreator {
    private static Cookie someNameForCookie = new Cookie("Name", "Value");
    public static String createDnsPageHTML(String url){
        System.setProperty("webdriver.chrome.driver","selenium\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.manage().addCookie(someNameForCookie);
        driver.get(url+"/characteristics/");
        Document document;
        String result = "Введите валидную ссылку";
        try {
            document = Jsoup.parse(driver.getPageSource());
            Element element = document
                    .select("div.product-card-tabs__contents")
                    .first();
            result = build(createFeaturesDNS(element), createCharacteristicsDNS(element));
        }finally {
            driver.quit();
            return result;
        }
    }

    public static String build(List<String > character,List<String > ttx){
        StringBuilder result = new StringBuilder(character.get(0)+".\n\n");
        result.append(ttx.get(0)+"\n").append("<strong>Особенности:<strong>\n\n");
        for (int i = 1;i<character.size();i++){
            result.append("- " + character.get(i)+";\n");
        }
        result.replace(result.length()-2,result.length(),"\n\n");
        result.append("<strong>Комплектация:<strong>\n\n");

        result.append(ttx.get(1));
        return result.toString();
    }

}