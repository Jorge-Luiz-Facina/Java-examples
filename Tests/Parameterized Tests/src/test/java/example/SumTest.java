package example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumTest {


    private static Stream<Arguments> numbers() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(2, 2, 4),
                Arguments.of(-4, -5, -9)
        );
    }

    @ParameterizedTest
    @MethodSource("numbers")
    public void testInteger(Integer x, Integer y, Integer expected){
        assertEquals(expected, Sum.integer(x, y));
    }
}
