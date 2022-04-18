package org.example.form.field.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.input.TextFieldInput;
import org.example.form.field.output.TextFieldOutput;
import org.springframework.http.HttpStatus;

public class TextFieldOperation extends AbstractFieldOperation<TextFieldOutput, TextFieldInput>{

    public TextFieldOperation(FieldTemplate fieldTemplate, Object object, Boolean isOutput) {
        super(fieldTemplate, object, isOutput);
    }

    @Override
    public TextFieldInput getInput(Object input) throws CustomException {
        try{
            return new ObjectMapper().convertValue(input, TextFieldInput.class);
        }catch (Exception e){
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FIELD_TYPE_ERROR_INPUT));
        }
    }

    @SneakyThrows
    @Override
    protected TextFieldOutput getOutput(String output) {
        return new ObjectMapper().readValue(output,  TextFieldOutput.class);
    }

    @SneakyThrows
    @Override
    protected TextFieldOutput getOutput(Object output) {
        try{
            return new ObjectMapper().convertValue(output, TextFieldOutput.class);
        }catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FIELD_TYPE_ERROR_OUTPUT));
        }
    }
}
