package org.example.form.field.rule;

import org.example.form.dto.request.FieldDataRequest;
import org.example.form.entity.FieldTemplate;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.operation.TextFieldListOperation;

public class TextFieldListRule extends AbstractFieldRule {

    @Override
    public Boolean check(FieldTemplate fieldTemplate) {
        return fieldTemplate.getType().equals(FieldTypeEnum.TEXT_FIELD_LIST);
    }

    @Override
    public Object execute(FieldTemplate fieldTemplate, FieldDataRequest fieldDataRequest) throws CustomException {
        errorRequestIsNull(fieldTemplate, fieldDataRequest);
        TextFieldListOperation textFieldListOperations = new TextFieldListOperation(fieldTemplate, fieldDataRequest.getInput(), false);
        textFieldListOperations.errorIsRequired();
        textFieldListOperations.errorIsNotMultiple();
        textFieldListOperations.errorContainsValue();
        return textFieldListOperations.getInput();
    }
}