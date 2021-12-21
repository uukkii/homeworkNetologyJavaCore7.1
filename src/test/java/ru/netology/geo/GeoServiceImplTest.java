package ru.netology.geo;

import org.junit.jupiter.api.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {

    final String ipRus = "172.25.46.58";
    final String ipUsa = "96.25.46.58";

    private static final String time = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss "));

    @BeforeAll
    static void messageBeforeAll() {
        System.out.println(time + "- tests of GeoServiceImpl started.");
    }

    @AfterAll
    static void messageAfterAll() {
        System.out.println(time + "- tests of GeoServiceImpl finished.");
    }


    @Test
    void byIpRusTest() {
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        GeoService geoService = new GeoServiceImpl();
        Location actual = geoService.byIp(ipRus);
        assertEquals(expected.getCountry(), actual.getCountry());
    }

    @Test
    void byIpEngTest() {
        Location expected = new Location("New Your", Country.USA, null, 0);
        GeoService geoService = new GeoServiceImpl();
        Location actual = geoService.byIp(ipUsa);
        assertEquals(expected.getCountry(), actual.getCountry());
    }
}
