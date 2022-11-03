package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.text.NumberFormat;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NumberFormatTest {

    @Test
    public void test() {
        var numberFormat = NumberFormat.getCompactNumberInstance(Locale.forLanguageTag("pt-br"), NumberFormat.Style.LONG);
        assertEquals("1 mil", numberFormat.format(1000));
        assertEquals("100 mil", numberFormat.format(100000));
        assertEquals("1 milhão", numberFormat.format(1000000));
        assertEquals("1 bilhão", numberFormat.format(1000000000));
    }
}
