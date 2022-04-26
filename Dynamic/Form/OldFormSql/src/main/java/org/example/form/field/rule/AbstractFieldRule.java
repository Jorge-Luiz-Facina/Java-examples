package org.example.form.field.rule;

import org.example.form.dto.request.FieldDataRequest;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import org.springframework.http.HttpStatus;
import java.util.Optional;

public abstract class AbstractFieldRule {

    public abstract Boolean check(FieldTemplate fieldTemplate, Optional<FieldDataRequest> fieldDataRequest);

    public abstract Object execute(FieldTemplate fieldTemplate, FieldDataRequest fieldDataRequest) throws CustomException;
}
