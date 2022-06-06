package org.example.hexagonal.application.ports.in;

import org.example.hexagonal.application.core.domain.Book;

public interface SaveBookInPort {

    Book save(Book book);
}
