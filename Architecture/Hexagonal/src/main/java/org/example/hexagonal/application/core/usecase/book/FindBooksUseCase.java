package org.example.hexagonal.application.core.usecase.book;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.application.core.domain.Book;
import org.example.hexagonal.application.ports.in.FindBooksInPort;
import org.example.hexagonal.application.ports.out.FindBooksOutPort;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FindBooksUseCase implements FindBooksInPort {

    private final FindBooksOutPort findBooksOutPort;

    @Override
    public List<Book> find(String name) {
        return findBooksOutPort.find(name);
    }
}
