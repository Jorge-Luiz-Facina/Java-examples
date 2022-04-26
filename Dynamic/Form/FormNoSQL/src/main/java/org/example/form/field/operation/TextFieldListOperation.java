package org.example.form.field.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.input.TextFieldListInput;
import org.example.form.field.output.TextFieldListOutput;
import org.example.form.message.FieldMessage;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class TextFieldListOperation extends AbstractFieldOperation<TextFieldListOutput, TextFieldListInput> {

    public TextFieldListOperation(FieldTemplate fieldTemplate, Object input, Boolean isOutput) throws CustomException {
        super(fieldTemplate, input, isOutput);
    }

    public void errorIsNotMultiple() throws CustomException {
        if (input.multipleOk() && !output.getIsMultiple()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.ESSE_CAMPO_NAO_E_MULTIPLO));
        }
    }

    public void errorContainsValue() throws CustomException {
        if (!output.getValues().containsAll(input.getValues())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.VALORES_DIFERENTES_DO_ESPERADO));
        }
    }

    @Override
    public TextFieldListInput getInput(Object input) throws CustomException {
        try {
            return new ObjectMapper().convertValue(input, TextFieldListInput.class);
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.TIPO_DE_CAMPO_DE_ENTRADA_NAO_ESPERADO));
        }
    }

    @Override
    protected TextFieldListOutput getOutput(Object output) throws CustomException {
        TextFieldListOutput textFieldListOutput = null;
        try {
            textFieldListOutput = new ObjectMapper().convertValue(output, TextFieldListOutput.class);
            if (Objects.isNull(textFieldListOutput)) {
                errorTipoDeCampoDeSaidaNaoEsperado();
            }
        } catch (Exception e) {
            errorTipoDeCampoDeSaidaNaoEsperado();
        }
        return textFieldListOutput;
    }

    @Override
    public void errorIsRequired() throws CustomException {
        if (fieldTemplate.getIsRequired() && !input.requiredOk()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.CAMPO_OBRIGATORIO));
        }
    }
    
    private void errorTipoDeCampoDeSaidaNaoEsperado() throws CustomException {
        throw new CustomException(HttpStatus.BAD_REQUEST, buildError(FieldMessage.TIPO_DE_CAMPO_DE_SAIDA_NAO_ESPERADO));
    }
}
