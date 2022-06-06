package org.example.hexagonal.application.core.usecase.book;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.application.core.domain.Book;
import org.example.hexagonal.application.ports.in.SaveBookInPort;
import org.example.hexagonal.application.ports.out.FindTagOutPort;
import org.example.hexagonal.application.ports.out.ProducerBookOutPort;
import org.example.hexagonal.application.ports.out.SaveBookOutPort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveBookUseCase implements SaveBookInPort {

    private final SaveBookOutPort saveBookOutPort;
    private final FindTagOutPort findTagOutPort;
    private final ProducerBookOutPort producerBookOutPort;

    @Override
    public Book save(Book book) {
        var tags = findTagOutPort.find(book.getName());
        //if you have rules check
        book.setTags(tags);
        saveBookOutPort.save(book);
        producerBookOutPort.producer(book);
        return book;
    }
}
