package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class ToListTest {

    @Test
    public void test() {

        String cardName1 = "test1";
        String cardName2 = "test2";

        List<String> cardNameList = List.of(cardName1, cardName2);

        List<Card> cardList = cardNameList.stream().map(Card::new).toList();
        assertEquals(cardList.get(0).name, cardName1);
        assertEquals(cardList.get(1).name, cardName2);
    }

    class Card {
        public String name;

        public Card(String name) {
            this.name = name;
        }
    }
}


