package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.Parameter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {
    final String RUS_IP = "172.0.0.0";
    final String USA_IP = "96.0.0.0";

    GeoServiceImpl geoService = new GeoServiceImpl();

    @ParameterizedTest
    @ValueSource(strings = {
            RUS_IP,
            USA_IP,
            GeoServiceImpl.MOSCOW_IP,
            GeoServiceImpl.NEW_YORK_IP,
            GeoServiceImpl.LOCALHOST})
    void locationByIpTest(String ip) {
        Country expectedCountry;
        switch (ip) {
            case RUS_IP:
            case GeoServiceImpl.MOSCOW_IP:
                expectedCountry = Country.RUSSIA;
                break;
            case USA_IP:
            case GeoServiceImpl.NEW_YORK_IP:
                expectedCountry = Country.USA;
                break;
            default:
                expectedCountry = null;
        }

        Country resultTestCountry = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(expectedCountry, resultTestCountry);
    }


}
