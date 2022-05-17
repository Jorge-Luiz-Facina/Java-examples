package org.example.entity;

import lombok.Data;
import org.example.enums.TypeEnum;

import java.util.List;

@Data
public class UserEntity {

    private Long id;
    private String nameUser;
    private Integer age;
    private AddressEntity addressTeste1;
    private AddressEntity addressTeste2;
    private List<DocumentEntity> documentsTeste;
    private TypeEnum type;
}
