package example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -3})
    public void testIsNegative(Integer number) {
        assertTrue(Number.isNegative(number));
    }

}
