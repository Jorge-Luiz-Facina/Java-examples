package org.example.mapper;

import org.example.dto.BookDTO;
import org.example.entity.AuthorEntity;
import org.example.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookEntityToBookDTO(BookEntity bookEntity);


    BookEntity bookDTOToBookEntity(BookDTO bookDTO);

    default String fromAuthorEntity(AuthorEntity author) {
        return author == null ? null : author.getName();
    }

    default AuthorEntity fromStringToCustomer(String authorName) {
        if (Objects.isNull(authorName)) {
            return null;
        }
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName(authorName);
        return authorEntity;
    }

}
