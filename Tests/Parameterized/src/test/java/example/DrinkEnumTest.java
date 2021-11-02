package example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrinkEnumTest {

    @ParameterizedTest
    @EnumSource(value = DrinkEnum.class, names = {"BEER", "WINE", "CHAMPAGNE"})
    public void testDrinkIsAlcohol(DrinkEnum drinkEnum) {
        assertTrue(drinkEnum.isAlcohol);
    }

    @ParameterizedTest
    @EnumSource(value = DrinkEnum.class, names = {"SODA", "JUICE"})
    public void testDrinkIsNotAlcohol(DrinkEnum drinkEnum) {
        assertFalse(drinkEnum.isAlcohol);
    }
}
