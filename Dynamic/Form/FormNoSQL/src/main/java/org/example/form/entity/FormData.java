package org.example.form.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.List;

@Data
@Document
public class FormData {

    @Id
    private String id;

    @DocumentReference(lazy = true)
    private FormTemplate formTemplate;

    private List<FieldData> fieldsData;
}
