package org.example.form.field.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.input.TextFieldInput;
import org.example.form.field.output.TextFieldOutput;
import org.example.form.message.FieldMessage;
import org.springframework.http.HttpStatus;

public class TextFieldOperation extends AbstractFieldOperation<TextFieldOutput, TextFieldInput> {

    public TextFieldOperation(FieldTemplate fieldTemplate, Object object, Boolean isOutput) throws CustomException {
        super(fieldTemplate, object, isOutput);
    }

    @Override
    public TextFieldInput getInput(Object input) throws CustomException {
        try {
            return new ObjectMapper().convertValue(input, TextFieldInput.class);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.TIPO_DE_CAMPO_DE_ENTRADA_NAO_ESPERADO));
        }
    }

    @Override
    protected TextFieldOutput getOutput(Object output) throws CustomException {
        try {
            return new ObjectMapper().convertValue(output, TextFieldOutput.class);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.TIPO_DE_CAMPO_DE_SAIDA_NAO_ESPERADO));
        }
    }

    @Override
    public void errorIsRequired() throws CustomException {
       if (fieldTemplate.getIsRequired() && !input.requiredOk()) {
           throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.CAMPO_OBRIGATORIO));
       }
    }
}
