package org.example.form.field.operation;

import lombok.Data;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import java.util.Objects;

@Data
public abstract class AbstractFieldOperation<T, I> {

    protected FieldTemplate fieldTemplate;
    protected T output;
    protected I input;

    public AbstractFieldOperation(FieldTemplate fieldTemplate, Object object, Boolean isOutput) throws CustomException {
        this.fieldTemplate = fieldTemplate;
        this.output = getOutput(object, isOutput);
        this.input = isOutput ? null : getInput(object);
    }

    public abstract I getInput(Object input) throws CustomException;

    protected abstract T getOutput(Object output) throws CustomException;

    protected abstract void errorIsRequired() throws CustomException;

    public Boolean isTypeOutput() {
        return Objects.nonNull(output);
    }

    private T getOutput(Object object, Boolean isOutput) throws CustomException {
        if (isOutput) {
            return getOutput(object);
        } else if (Objects.nonNull(fieldTemplate.getOutput())) {
            return getOutput(fieldTemplate.getOutput());
        }
        return null;
    }

    protected FieldError buildError(String message) {
        FieldError fieldError = FieldError.to(fieldTemplate, input, output, message);
        return fieldError;
    }
}
