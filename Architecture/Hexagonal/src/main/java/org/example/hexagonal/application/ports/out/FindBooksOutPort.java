package org.example.hexagonal.application.ports.out;

import org.example.hexagonal.application.core.domain.Book;
import java.util.List;

public interface FindBooksOutPort {

    List<Book> find(String Name);
}
