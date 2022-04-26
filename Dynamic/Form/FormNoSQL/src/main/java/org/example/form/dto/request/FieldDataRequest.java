package org.example.form.dto.request;

import lombok.Data;
import org.example.form.entity.FieldData;
import org.example.form.entity.FieldTemplate;
import javax.validation.constraints.NotNull;

@Data
public class FieldDataRequest {

    @NotNull
    private String fieldTemplateId;

    @NotNull
    private Object input;

    public FieldData toEntity(FieldTemplate fieldTemplate, Object value) {
        FieldData fieldData = new FieldData();
        fieldData.setFieldTemplateId(fieldTemplate.getId());
        fieldData.setInput(value);
        return fieldData;
    }
}
