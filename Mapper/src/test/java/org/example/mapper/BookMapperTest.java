package org.example.mapper;

import org.example.dto.BookDTO;
import org.example.entity.BookEntity;
import org.example.util.RandomValueUtil;
import org.example.util.ReflectionFieldUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookMapperTest {

    @Test
    public void bookEntityToBookDTO_NoNull_Ok_Test() {
        var bookEntity = RandomValueUtil.generate(BookEntity.class);
        var bookDTO = BookMapper.INSTANCE.bookEntityToBookDTO(bookEntity);
        ReflectionFieldUtil.verifyFields(bookDTO);
    }

    @Test
    public void bookDTOToBookEntity_NoNull_Ok_Test() {
        var bookDTO = RandomValueUtil.generate(BookDTO.class);
        var bookEntity = BookMapper.INSTANCE.bookDTOToBookEntity(bookDTO);
        ReflectionFieldUtil.verifyFields(bookEntity);
    }
}
