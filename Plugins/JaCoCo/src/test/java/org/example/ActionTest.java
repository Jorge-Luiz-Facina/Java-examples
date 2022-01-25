package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    @Test
    public void pairTest(){
        Action action = new Action();

        String pair = action.pairOrOdd(2);
        assertEquals("Pair", pair);
    }

    @Test
    public void oddTest(){
        Action action = new Action();

        String odd = action.pairOrOdd(3);
        assertEquals("Odd", odd);
    }
}