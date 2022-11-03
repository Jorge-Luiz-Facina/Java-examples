package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IntanceOfTest {

    @Test
    public void test() {
        Object object = new Car();
        if (object instanceof Car car && Objects.isNull(car.name)) {
            assertNull(car.name);
        }
    }
    class Car {
        public String name;
    }
}


