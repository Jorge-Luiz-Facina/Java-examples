package org.example.form.field.rule;

import org.example.form.dto.request.FieldDataRequest;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import org.example.form.message.FieldMessage;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public abstract class AbstractFieldRule {

    public abstract Boolean check(FieldTemplate fieldTemplate);

    public abstract Object execute(FieldTemplate fieldTemplate, FieldDataRequest fieldDataRequest) throws CustomException;

    protected void errorRequestIsNull(FieldTemplate fieldTemplate, FieldDataRequest fieldDataRequest) throws CustomException {
        if (Objects.isNull(fieldDataRequest)) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    FieldError.to(fieldTemplate, null, null, FieldMessage.OS_CAMPOS_NAO_PODEM_FICAR_VAZIO));
        }
    }
}
