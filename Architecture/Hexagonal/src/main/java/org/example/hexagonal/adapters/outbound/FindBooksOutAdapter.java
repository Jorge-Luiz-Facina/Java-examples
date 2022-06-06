package org.example.hexagonal.adapters.outbound;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.adapters.inbound.entity.BookEntity;
import org.example.hexagonal.adapters.inbound.mapper.BookMapper;
import org.example.hexagonal.adapters.outbound.repository.BookRepository;
import org.example.hexagonal.application.core.domain.Book;
import org.example.hexagonal.application.ports.out.FindBooksOutPort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FindBooksOutAdapter implements FindBooksOutPort {

    private final BookRepository bookRepository;

    @Override
    public List<Book> find(String name) {
        List<BookEntity> bookEntities = bookRepository.findByName(name);
        return BookMapper.INSTANCE.bookEntitiesToBooks(bookEntities);
    }
}
