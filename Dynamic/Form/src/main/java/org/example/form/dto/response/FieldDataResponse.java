package org.example.form.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.example.form.entity.FieldData;
import org.example.form.enums.FieldTypeEnum;

import java.util.Objects;

@Builder
@Data
public class FieldDataResponse {

    private Long id;
    private Integer position;
    private Object input;
    private String name;
    private Boolean isRequired;
    private FieldTypeEnum type;
    private Object output;

    @SneakyThrows
    public static FieldDataResponse to(FieldData fieldData) {
        return FieldDataResponse.builder().
                id(fieldData.getId()).
                position(fieldData.getFieldTemplate().getPosition()).
                input(Objects.isNull(fieldData.getValue()) ?  null :
                        new ObjectMapper().readValue(fieldData.getValue(), Object.class)).
                name(fieldData.getFieldTemplate().getName()).
                isRequired(fieldData.getFieldTemplate().getIsRequired()).
                type(fieldData.getFieldTemplate().getType()).
                output(Objects.isNull(fieldData.getFieldTemplate().getOutput()) ?  null :
                        new ObjectMapper().readValue(fieldData.getFieldTemplate().getOutput(), Object.class)).
                build();
    }
}
