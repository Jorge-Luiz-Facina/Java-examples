package org.example.form.field.rule;

import org.example.form.dto.request.FieldDataRequest;
import org.example.form.entity.FieldTemplate;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.operation.TextFieldListOperation;

import java.util.Optional;

public class TextFieldListRule extends AbstractFieldRule {

    @Override
    public Boolean check(FieldTemplate fieldTemplate, Optional<FieldDataRequest> fieldDataRequest) throws CustomException {
        return super.check(fieldTemplate, fieldDataRequest) && fieldTemplate.getType().equals(FieldTypeEnum.TEXT_FIELD_LIST);
    }

    @Override
    public Object execute(FieldTemplate fieldTemplate, FieldDataRequest fieldDataRequest) throws CustomException {
        TextFieldListOperation textFieldListOperations = new TextFieldListOperation(fieldTemplate, fieldDataRequest.getValue(), false);
        textFieldListOperations.errorIsNotMultiple();
        textFieldListOperations.containsValue();
        return textFieldListOperations.getInput();
    }
}