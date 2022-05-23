package org.example.mapper;

import org.example.dto.UserDTO;
import org.example.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DocumentMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "nameUser", target = "name"),
            @Mapping(source = "addressTeste1", target = "address1"),
            @Mapping(source = "addressTeste2", target = "address2"),
            @Mapping(source = "documentsTeste", target = "documents", qualifiedByName = "documentEntityToDocumentDTO"),
            @Mapping(source = "addressTeste1.streetName", target = "address1.street"),
            @Mapping(source = "addressTeste2.streetName", target = "address2.street"),
            @Mapping(source = "addressTeste1.country", target = "country")
    })
    UserDTO userEntityToUserDTO(UserEntity userEntity);
}