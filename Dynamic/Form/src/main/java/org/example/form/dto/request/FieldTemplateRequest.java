package org.example.form.dto.request;

import lombok.Data;
import lombok.SneakyThrows;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.field.operation.TextFieldListOperation;

@Data
public class FieldTemplateRequest {

    private String name;
    private Integer position;
    private FieldTypeEnum type;
    private Boolean isRequired;
    private Object output;

    public FieldTemplate toEntity(FormTemplate formTemplate) {
        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setName(name);
        fieldTemplate.setIsRequired(isRequired);
        fieldTemplate.setPosition(position);
        fieldTemplate.setType(type);
        fieldTemplate.setFormTemplate(formTemplate);
        toOutput(fieldTemplate);
        return fieldTemplate;
    }

    @SneakyThrows
    private void toOutput(FieldTemplate fieldTemplate) {

        if(fieldTemplate.getType().equals(FieldTypeEnum.TEXT_FIELD)) {
            return;
        }
        TextFieldListOperation textFieldListOperation = new TextFieldListOperation(fieldTemplate, output, true);
        if(fieldTemplate.getType().equals(FieldTypeEnum.TEXT_FIELD_LIST) && textFieldListOperation.isTypeOutput()) {
            fieldTemplate.setOutput(textFieldListOperation.outputToJson());
        }
    }
}
