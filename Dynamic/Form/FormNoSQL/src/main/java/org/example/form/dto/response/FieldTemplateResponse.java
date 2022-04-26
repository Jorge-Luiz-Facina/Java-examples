package org.example.form.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.form.entity.FieldTemplate;
import org.example.form.enums.FieldTypeEnum;

@Data
@Builder
public class FieldTemplateResponse {

    private String id;
    private String name;
    private Integer position;
    private FieldTypeEnum type;
    private Boolean isRequired;
    private Boolean isActive;
    private Object output;

    public static FieldTemplateResponse to(FieldTemplate fieldTemplate) {
        return FieldTemplateResponse.builder().
                id(fieldTemplate.getId()).
                name(fieldTemplate.getName()).
                position(fieldTemplate.getPosition()).
                type(fieldTemplate.getType()).
                isRequired(fieldTemplate.getIsRequired()).
                isActive(fieldTemplate.getIsActive()).
                output(fieldTemplate.getOutput()).
                build();
    }

}
