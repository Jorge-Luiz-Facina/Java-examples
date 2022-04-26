package org.example.form.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.form.entity.FieldTemplate;
import org.example.form.enums.FieldTypeEnum;

@Builder
@Data
public class FieldDataResponse {

    private String fieldTemplateId;
    private Object input;
    private String name;
    private Boolean isRequired;
    private FieldTypeEnum type;
    private Object output;

    public static FieldDataResponse to(FieldTemplate fieldTemplate, Object input) {
        return FieldDataResponse.builder().
                fieldTemplateId(fieldTemplate.getId()).
                input(input).
                name(fieldTemplate.getName()).
                isRequired(fieldTemplate.getIsRequired()).
                type(fieldTemplate.getType()).
                output(fieldTemplate.getOutput()).
                build();
    }
}
