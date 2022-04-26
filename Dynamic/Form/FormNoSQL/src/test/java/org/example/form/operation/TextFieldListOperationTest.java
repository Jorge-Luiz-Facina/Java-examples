package org.example.form.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import org.example.form.field.input.TextFieldInput;
import org.example.form.field.input.TextFieldListInput;
import org.example.form.field.operation.TextFieldListOperation;
import org.example.form.field.output.TextFieldListOutput;
import org.example.form.field.output.TextFieldOutput;
import org.example.form.message.FieldMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TextFieldListOperationTest {

    @Test
    public void getInputTipoDeCampoDeEntradaNaoEsperadoTest() {
        CustomException exception = assertThrows(CustomException.class, () -> {
           new TextFieldListOperation(new FieldTemplate(), new TextFieldInput(), false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getError() instanceof FieldError);
        FieldError fieldError = new ObjectMapper().convertValue(exception.getError(), FieldError.class);
        assertEquals(FieldMessage.TIPO_DE_CAMPO_DE_ENTRADA_NAO_ESPERADO, fieldError.getMessage());
    }

    @Test
    public void getOutputTipoDeCampoDeSaidaNaoEsperadoTest() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            new TextFieldListOperation(new FieldTemplate(), new TextFieldOutput(), true);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getError() instanceof FieldError);
        FieldError fieldError = new ObjectMapper().convertValue(exception.getError(), FieldError.class);
        assertEquals(FieldMessage.TIPO_DE_CAMPO_DE_SAIDA_NAO_ESPERADO, fieldError.getMessage());
    }

    @Test
    public void errorIsRequiredTest() throws CustomException {
        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setIsRequired(true);
        TextFieldListOperation textFieldOperation = new TextFieldListOperation(fieldTemplate, new TextFieldListInput(), false);
        CustomException exception = assertThrows(CustomException.class, () -> {
            textFieldOperation.errorIsRequired();
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getError() instanceof FieldError);
        FieldError fieldError = new ObjectMapper().convertValue(exception.getError(), FieldError.class);
        assertEquals(FieldMessage.CAMPO_OBRIGATORIO, fieldError.getMessage());
    }

    @Test
    public void errorIsNotMultipleTest() throws CustomException {
        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setIsRequired(true);

        TextFieldListOutput textFieldListOutput = new TextFieldListOutput();
        textFieldListOutput.setIsMultiple(false);
        fieldTemplate.setOutput(textFieldListOutput);
        TextFieldListInput textFieldListInput = new TextFieldListInput();
        textFieldListInput.setValues(Arrays.asList("aaa", "bbb"));
        TextFieldListOperation textFieldOperation = new TextFieldListOperation(fieldTemplate, textFieldListInput, false);
        CustomException exception = assertThrows(CustomException.class, () -> {
            textFieldOperation.errorIsNotMultiple();
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getError() instanceof FieldError);
        FieldError fieldError = new ObjectMapper().convertValue(exception.getError(), FieldError.class);
        assertEquals(FieldMessage.ESSE_CAMPO_NAO_E_MULTIPLO, fieldError.getMessage());
    }

    @Test
    public void errorContainsValueTest() throws CustomException {
        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setIsRequired(true);

        TextFieldListOutput textFieldListOutput = new TextFieldListOutput();
        textFieldListOutput.setValues(Arrays.asList("ewqewq", "qqq"));
        textFieldListOutput.setIsMultiple(true);
        fieldTemplate.setOutput(textFieldListOutput);
        TextFieldListInput textFieldListInput = new TextFieldListInput();
        textFieldListInput.setValues(Arrays.asList("dd", "ff"));
        TextFieldListOperation textFieldOperation = new TextFieldListOperation(fieldTemplate, textFieldListInput, false);
        CustomException exception = assertThrows(CustomException.class, () -> {
            textFieldOperation.errorContainsValue();
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getError() instanceof FieldError);
        FieldError fieldError = new ObjectMapper().convertValue(exception.getError(), FieldError.class);
        assertEquals(FieldMessage.VALORES_DIFERENTES_DO_ESPERADO, fieldError.getMessage());
    }
}
