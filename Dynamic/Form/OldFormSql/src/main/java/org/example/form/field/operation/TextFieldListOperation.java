package org.example.form.field.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.input.TextFieldListInput;
import org.example.form.field.output.TextFieldListOutput;
import org.springframework.http.HttpStatus;

public class TextFieldListOperation extends AbstractFieldOperation<TextFieldListOutput, TextFieldListInput> {


    public TextFieldListOperation(FieldTemplate fieldTemplate, Object input, Boolean isOutput) {
        super(fieldTemplate, input, isOutput);
    }

    public void errorIsNotMultiple() throws CustomException {
        if (input.multipleOk() && !output.getIsMultiple()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError("field no multiple"));
        }
    }

    public void containsValue() throws CustomException {
        if (!output.getValues().containsAll(input.getValues())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError("Values different from expected"));
        }
    }

    @Override
    public TextFieldListInput getInput(Object input) throws CustomException {
        try{
            return new ObjectMapper().convertValue(input, TextFieldListInput.class);
        }catch (Exception e){
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FIELD_TYPE_ERROR_INPUT));
        }
    }

    @SneakyThrows
    @Override
    protected TextFieldListOutput getOutput(String output) {
        return new ObjectMapper().readValue(output,  TextFieldListOutput.class);
    }

    @SneakyThrows
    @Override
    protected TextFieldListOutput getOutput(Object output) {
        try{
            return new ObjectMapper().convertValue(output, TextFieldListOutput.class);
        }catch (Exception e){
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FIELD_TYPE_ERROR_OUTPUT));
        }
    }

    @Override
    public void errorIsRequired() throws CustomException {
        if(fieldTemplate.getIsRequired() && !input.requiredOk()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError("Campo obrigatorio"));
        }
    }
}
