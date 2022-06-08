package org.example.mapper;

import org.example.dto.DocumentDTO;
import org.example.entity.DocumentEntity;
import org.example.entity.OtherEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentEntity cloneDocumentEntity(DocumentEntity documentEntity);

    @Mapping(source = "typeName", target = "type")
    @Named("documentEntityToDocumentDTO")
    DocumentDTO documentEntityToDocumentDTO(DocumentEntity documentEntity);

    @Mapping(source = "documentDTO.type", target = "typeName")
    @Mapping(source = "otherEntity", target = "other")
    DocumentEntity documentDTOToDocumentEntity(DocumentDTO documentDTO, OtherEntity otherEntity);

    @InheritInverseConfiguration(name = "documentEntityToDocumentDTO")
    void documentDTOMergeToDocumentEntity(DocumentDTO documentDTO, @MappingTarget DocumentEntity documentEntity);

    @Mapping(source = "type", target = "typeName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void documentDTOUpdateToDocumentEntity(DocumentDTO documentDTO, @MappingTarget DocumentEntity documentEntity);


    @IterableMapping(qualifiedByName = "documentEntityToDocumentDTO")
    List<DocumentDTO> documentEntitysToDocumentDTOs(List<DocumentEntity> documentEntitys);

}
