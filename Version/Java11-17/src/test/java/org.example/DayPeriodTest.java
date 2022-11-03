package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DayPeriodTest {

    @Test
    public void test() {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("B").withLocale(Locale.forLanguageTag("pt-br"));
        assertEquals("meia-noite", dateTimeFormatter.format(LocalTime.of(0, 0)));
        assertEquals("da madrugada", dateTimeFormatter.format(LocalTime.of(1, 0)));
        assertEquals("da manhã", dateTimeFormatter.format(LocalTime.of(6, 0)));
        assertEquals("meio-dia", dateTimeFormatter.format(LocalTime.of(12, 0)));
        assertEquals("da tarde", dateTimeFormatter.format(LocalTime.of(13, 0)));
        assertEquals("da noite", dateTimeFormatter.format(LocalTime.of(19, 0)));
    }

}
