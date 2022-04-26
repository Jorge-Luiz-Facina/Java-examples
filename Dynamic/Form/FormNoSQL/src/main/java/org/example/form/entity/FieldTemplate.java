package org.example.form.entity;

import lombok.Data;
import org.example.form.enums.FieldTypeEnum;

@Data
public class FieldTemplate {

    private String id;

    private String name;

    private Boolean isActive;

    private Integer position;

    private FieldTypeEnum type;

    private Boolean isRequired;

    private Object output;

}
