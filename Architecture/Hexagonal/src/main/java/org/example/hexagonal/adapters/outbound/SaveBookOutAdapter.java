package org.example.hexagonal.adapters.outbound;

import lombok.RequiredArgsConstructor;
import org.example.hexagonal.adapters.inbound.entity.BookEntity;
import org.example.hexagonal.adapters.inbound.entity.TagEntity;
import org.example.hexagonal.adapters.inbound.mapper.BookMapper;
import org.example.hexagonal.adapters.outbound.repository.BookRepository;
import org.example.hexagonal.adapters.outbound.repository.TagRepository;
import org.example.hexagonal.application.core.domain.Book;
import org.example.hexagonal.application.ports.out.SaveBookOutPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SaveBookOutAdapter implements SaveBookOutPort {

    private final BookRepository bookRepository;
    private final TagRepository tagRepository;

    @Override
    @Transactional
    public Book save(Book book) {
        BookEntity bookEntity = BookMapper.INSTANCE.bookToBookEntity(book);
        bookEntity.getTags().forEach(tag -> {
            TagEntity tagFind = tagRepository.findByName(tag.getName()).orElse(null);
            tag.setId(Optional.ofNullable(tagFind).map(TagEntity::getId).orElse(null));
        });
        bookRepository.save(bookEntity);
        return book;
    }

}
