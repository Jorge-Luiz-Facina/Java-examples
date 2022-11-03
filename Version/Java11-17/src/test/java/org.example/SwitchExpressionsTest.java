package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SwitchExpressionsTest {

    @Test
    public void test() {
        assertEquals("Terrestrial", getTypeAnimal(AnimalEnum.CAT));
        assertEquals("Flying", getTypeAnimal(AnimalEnum.SEAGULL));
    }

    private static String getTypeAnimal(AnimalEnum animalEnum) {
        return switch (animalEnum) {
            case DOG, CAT -> {
                System.out.println(animalEnum.name());
                yield "Terrestrial";
            }
            case SEAGULL -> "Flying";
//            case null -> "It is null"; preview
//            default -> "default";
        };
    }

    enum AnimalEnum {
        DOG, CAT, SEAGULL,
    }
}
