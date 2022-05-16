package org.example.mapper;

import org.example.dto.DocumentDTO;
import org.example.entity.DocumentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface DocumentMapper {

    @Mapping(source = "typeName", target = "type")
    @Named("DocumentEntityToDocumentDTO")
    DocumentDTO DocumentEntityToDocumentDTO(DocumentEntity documentEntity);
}
