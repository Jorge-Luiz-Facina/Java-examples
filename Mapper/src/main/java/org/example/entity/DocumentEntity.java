package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.dto.AddressDTO;

@Data
@AllArgsConstructor
public class DocumentEntity {

    private String typeName;
    private String code;
    private String codeWWW;
    private OtherEntity other;
}
