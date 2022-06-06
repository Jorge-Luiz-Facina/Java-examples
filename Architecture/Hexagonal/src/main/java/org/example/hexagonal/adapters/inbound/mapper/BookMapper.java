package org.example.hexagonal.adapters.inbound.mapper;

import org.example.hexagonal.adapters.inbound.entity.BookEntity;
import org.example.hexagonal.adapters.inbound.dto.request.BookRequest;
import org.example.hexagonal.application.core.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookRequestToBook(BookRequest bookRequest);

    @Mapping(target = "id", ignore = true)
    BookEntity bookToBookEntity(Book book);

    List<Book> bookEntitiesToBooks(List<BookEntity> bookEntities);
}
