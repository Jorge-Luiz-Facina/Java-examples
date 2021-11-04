package statics.method;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticUtilsTest {

    @Test
    void givenStaticMethodWithNoArgs_whenMocked_thenReturnsMockSuccessfully() {
        assertEquals(StaticUtils.name(), "Test");

        try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
            utilities.when(StaticUtils::name).thenReturn("_Test_");
            assertEquals(StaticUtils.name(), "_Test_");
        }

        assertEquals(StaticUtils.name(), "Test");
    }

    @Test
    void givenStaticMethodWithArgs_whenMocked_thenReturnsMockSuccessfully() {
        assertEquals(StaticUtils.wordReverse("Test"), "tseT");

        try (MockedStatic<StaticUtils> utilities = Mockito.mockStatic(StaticUtils.class)) {
            utilities.when(() -> StaticUtils.wordReverse("Test"))
                    .thenReturn("Test");

            assertEquals(StaticUtils.wordReverse("Test"), "Test");
        }

        assertEquals(StaticUtils.wordReverse("Test"), "tseT");
    }
}
