package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class ActionTest {

    @Test
    public void isPairTest() {
        Action sut = new Action();
        assertFalse(sut.isPair(1));
    }

//    @Test
//    public void isTest() {
//        Action sut = new Action();
//        assertTrue(sut.isPair(2));
//    }

}