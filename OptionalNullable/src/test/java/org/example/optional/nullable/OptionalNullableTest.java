package org.example.optional.nullable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OptionalNullableTest {

    @Test
    public void numberIsNull_Ok_Test() {
        Book book = null;

        Integer number = Optional.ofNullable(book)
                .map(Book::getAuthor)
                .map(Author::getAddress)
                .map(Address::getNumber).orElse(null);
        assertNull(number);
    }

    @Test
    public void numberNotNull_Ok_Test() {
        Book book = new Book();
        book.setAuthor(new Author());
        book.getAuthor().setAddress(new Address());
        book.getAuthor().getAddress().setNumber(1);
        Integer number = Optional.ofNullable(book)
                .map(Book::getAuthor)
                .map(Author::getAddress)
                .map(Address::getNumber).orElse(null);
        assertNotNull(number);
    }
}
