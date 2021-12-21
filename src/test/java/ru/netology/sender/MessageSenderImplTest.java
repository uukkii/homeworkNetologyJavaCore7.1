package ru.netology.sender;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MessageSenderImplTest {

    final String expectedRus = "Добро пожаловать";
    final String expectedEng = "Welcome";
    final String rusIp = "172.123.12.19";
    final String usaIp = "96.44.183.149";

    private static final String time = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss "));

    @BeforeAll
    static void messageBeforeAll() {
        System.out.println(time + " - test of MessageSenderImpl started.");
    }

    @AfterAll
    static void messageAfterAll() {
        System.out.println("\n" + time + " - test of MessageSenderImpl finished.");
    }

    @Test
    void messageSenderRusTxt() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(rusIp))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(expectedRus);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, rusIp);

        assertEquals(expectedRus,
                messageSender.send(headers));
    }

    @Test
    void messageSenderUsaTxt() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(usaIp))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(expectedEng);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, usaIp);

        assertEquals(expectedEng,
                messageSender.send(headers));
    }
}
