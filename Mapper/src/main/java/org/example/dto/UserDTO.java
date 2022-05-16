package org.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserDTO {

    private String name;
    private Integer age;
    private AddressDTO address1;
    private AddressDTO address2;
    private String country;
    private List<DocumentDTO> documents;
    private TypeEnumGenericDTO type;
}
