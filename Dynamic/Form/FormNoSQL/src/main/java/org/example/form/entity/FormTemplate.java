package org.example.form.entity;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import org.example.form.enums.SystemTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class FormTemplate {

    @Id
    private String id;

    private String name;

    private String type;

    private Integer version;

    private SystemTypeEnum system;

    private List<FieldTemplate> fieldTemplates =  new ArrayList<>();
}