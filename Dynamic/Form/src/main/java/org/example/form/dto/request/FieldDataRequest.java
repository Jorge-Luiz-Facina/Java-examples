package org.example.form.dto.request;

import lombok.Data;
import org.example.form.entity.FieldData;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormData;

@Data
public class FieldDataRequest {
    private Integer position;
    private Object value;

    public FieldData toEntity(FormData formData, FieldTemplate fieldTemplate, String valeuToJson) {
        FieldData fieldData = new FieldData();
        fieldData.setValue(valeuToJson);
        fieldData.setFieldTemplate(fieldTemplate);
        fieldData.setFormData(formData);
        formData.getFieldsData().add(fieldData);
        return fieldData;
    }
}
