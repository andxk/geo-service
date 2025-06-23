package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    private GeoServiceImpl      geoService = Mockito.mock(GeoServiceImpl.class);
    private LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
    private Map<String, String> headers = new HashMap<>();
    private MessageSenderImpl   messageSender = new MessageSenderImpl(geoService, localizationService);
//    private String expectedText;


    @Test
    @DisplayName("Проверка московского IP")
    public void sendMoscowIpThenReturnRusText() {
        headers.clear();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "", 0));

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        String expectedText = "Добро пожаловать";
        String returnedText = messageSender.send(headers);
        Assertions.assertEquals(expectedText, returnedText);
    }


    @Test
    @DisplayName("Проверка российского IP")
    public void sendRussianIpThenReturnRusText() {
        final String RUS_IP = "172.0.0.0";

        headers.clear();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, RUS_IP);

        Mockito.when(geoService.byIp(RUS_IP))
                .thenReturn(new Location("", Country.RUSSIA, "", 0));

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        String expectedText = "Добро пожаловать";
        String returnedText = messageSender.send(headers);
        Assertions.assertEquals(expectedText, returnedText);
    }


    @Test
    @DisplayName("Проверка ньюйоркского IP")
    public void sendNewYorkIpThenReturnEngText() {
        headers.clear();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        String expectedText = "Welcome";
        String returnedText = messageSender.send(headers);
        Assertions.assertEquals(expectedText, returnedText);
    }


    @Test
    @DisplayName("Проверка американского IP")
    public void sendUsaIpThenReturnEngText() {
        final String USA_IP = "96.0.0.0";

        headers.clear();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, USA_IP);

        Mockito.when(geoService.byIp(USA_IP))
                .thenReturn(new Location("", Country.USA, "", 0));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        String expectedText = "Welcome";
        String returnedText = messageSender.send(headers);
        Assertions.assertEquals(expectedText, returnedText);
    }



}
