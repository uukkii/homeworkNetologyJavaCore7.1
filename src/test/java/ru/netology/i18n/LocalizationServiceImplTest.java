package ru.netology.i18n;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class LocalizationServiceImplTest {

    private static final String time = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss "));

    @BeforeAll
    static void messageBeforeAll() {
        System.out.println(time + " - tests of LocalizationServiceImpl started.");
    }

    @AfterAll
    static void messageAfterAll() {
        System.out.println(time + " - tests of LocalizationServiceImpl finished.");
    }

    @Test
    void localTest() {
        String expected = "Добро пожаловать";
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(Country.RUSSIA);
        assertEquals(expected, actual);
    }
}
