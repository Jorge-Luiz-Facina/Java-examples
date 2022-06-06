package org.example.hexagonal.application.ports.in;

import org.example.hexagonal.application.core.domain.Book;

import java.util.List;

public interface FindBooksInPort {

    List<Book> find(String name);
}
