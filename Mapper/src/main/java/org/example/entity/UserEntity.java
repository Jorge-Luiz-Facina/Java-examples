package org.example.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserEntity {

    private String nameUser;
    private Integer age;
    private AddressEntity addressTeste1;
    private AddressEntity addressTeste2;
    private List<DocumentEntity> documentsTeste;
}
