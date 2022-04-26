package org.example.form.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.example.form.entity.FieldTemplate;
import org.example.form.enums.FieldTypeEnum;
import java.util.Objects;

@Data
@Builder
public class FieldTemplateResponse {

    private String name;
    private Integer position;
    private FieldTypeEnum type;
    private Boolean isRequired;
    private Object output;
    private Boolean isActive;

    @SneakyThrows
    public static FieldTemplateResponse to(FieldTemplate fieldTemplate) {
        return FieldTemplateResponse.builder().
                name(fieldTemplate.getName()).
                position(fieldTemplate.getPosition()).
                type(fieldTemplate.getType()).
                isActive(fieldTemplate.getIsActive()).
                isRequired(fieldTemplate.getIsRequired()).
                output(Objects.isNull(fieldTemplate.getOutput()) ?  null :
                        new ObjectMapper().readValue(fieldTemplate.getOutput(), Object.class)).
                build();
    }

}
