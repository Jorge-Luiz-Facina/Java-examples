package org.example.form.field.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.form.entity.FieldTemplate;
import org.example.form.enums.FieldTypeEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldError {
    private String fieldName;
    private Integer position;
    private FieldTypeEnum type;
    private Boolean isRequired;
    private Object output;
    private Object input;
    private String message;

    public static FieldError to(FieldTemplate fieldTemplate, Object input,
                                Object output, String message) {
        return FieldError.builder().
                fieldName(fieldTemplate.getName()).
                position(fieldTemplate.getPosition()).
                type(fieldTemplate.getType()).
                isRequired(fieldTemplate.getIsRequired()).
                message(message).
                output(output).
                input(input).
                build();
    }
}