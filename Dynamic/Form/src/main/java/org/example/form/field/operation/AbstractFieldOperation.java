package org.example.form.field.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import org.springframework.http.HttpStatus;
import java.util.Objects;

@Data
public abstract class AbstractFieldOperation<T, I> {

    protected FieldTemplate fieldTemplate;
    protected T output;
    protected I input;

    public static final String FIELD_TYPE_ERROR_OUTPUT = "field type error output";
    public static final String FIELD_TYPE_ERROR_INPUT = "field type error input";

    @SneakyThrows
    public AbstractFieldOperation(FieldTemplate fieldTemplate, Object object, Boolean isOutput) {
        this.fieldTemplate = fieldTemplate;
        this.output = getOutput(object, isOutput);
        this.input = isOutput ? null: getInput(object);
    }

    public abstract I getInput(Object input) throws CustomException;

    protected abstract T getOutput(String output);

    protected abstract T getOutput(Object output);

    public Boolean isTypeOutput() throws CustomException {
        try{
            return Objects.nonNull(output);
        }catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError("field type error Output"));
        }
    }

    private T getOutput(Object object, Boolean isOutput) {
        if(isOutput){
            return getOutput(object);
        }else if(Objects.nonNull(fieldTemplate.getOutput())){
            return getOutput(fieldTemplate.getOutput());
        }
        return null;
    }

    protected FieldError buildError(String message) {
        FieldError fieldError = FieldError.to(fieldTemplate, input, output, message);
        return fieldError;
    }

    @SneakyThrows
    public String outputToJson(){
        return new ObjectMapper().writeValueAsString(output);
    }
}
