package org.example.hexagonal.application.ports.out;

import org.example.hexagonal.application.core.domain.Book;

public interface SaveBookOutPort {

    Book save(Book book);
}
