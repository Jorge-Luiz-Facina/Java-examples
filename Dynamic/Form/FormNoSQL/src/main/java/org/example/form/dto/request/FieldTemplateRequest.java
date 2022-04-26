package org.example.form.dto.request;

import lombok.Data;
import org.bson.types.ObjectId;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.operation.TextFieldListOperation;
import javax.validation.constraints.NotNull;

@Data
public class FieldTemplateRequest {

    @NotNull
    private String name;
    @NotNull
    private Integer position;
    @NotNull
    private Boolean isActive;
    @NotNull
    private FieldTypeEnum type;
    @NotNull
    private Boolean isRequired;
    private Object output;

    public void toEntityCreate(FormTemplate formTemplate) throws CustomException {
        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setId(new ObjectId().toString());
        fieldTemplate.setName(name);
        fieldTemplate.setIsRequired(isRequired);
        fieldTemplate.setIsActive(isActive);
        fieldTemplate.setPosition(position);
        fieldTemplate.setType(type);
        toOutput(fieldTemplate);
        formTemplate.getFieldTemplates().add(fieldTemplate);
    }

    public void toEntityUpdate(FieldTemplate fieldTemplate) throws CustomException {
        fieldTemplate.setName(name);
        fieldTemplate.setIsRequired(isRequired);
        fieldTemplate.setIsActive(isActive);
        fieldTemplate.setPosition(position);
        fieldTemplate.setType(type);
        toOutput(fieldTemplate);
    }

    private void toOutput(FieldTemplate fieldTemplate) throws CustomException {
        if (fieldTemplate.getType().equals(FieldTypeEnum.TEXT_FIELD)) {
            return;
        }
        TextFieldListOperation textFieldListOperation = new TextFieldListOperation(fieldTemplate, output, true);
        if (fieldTemplate.getType().equals(FieldTypeEnum.TEXT_FIELD_LIST) && textFieldListOperation.isTypeOutput()) {
            fieldTemplate.setOutput(textFieldListOperation.getOutput());
        }
    }
}
