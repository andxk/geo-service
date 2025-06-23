package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationSetviceImplTest {
    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    void localeNotRussiaTest() {
        for(Country country : Country.values()) {
            if (Country.RUSSIA.equals(country)) continue;
            String expectedText = "Welcome";
            String returnedText = localizationService.locale(country);
            System.out.println(country.toString());
            Assertions.assertEquals(expectedText, returnedText);
        }
    }


    @Test
    void localeRussiaTest() {
        String expectedText = "Добро пожаловать";
        String returnedText = localizationService.locale(Country.RUSSIA);
        System.out.println(Country.RUSSIA);
        Assertions.assertEquals(expectedText, returnedText);

    }



}
