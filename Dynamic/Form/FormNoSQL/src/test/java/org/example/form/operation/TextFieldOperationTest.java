package org.example.form.operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.form.entity.FieldTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import org.example.form.field.input.TextFieldInput;
import org.example.form.field.input.TextFieldListInput;
import org.example.form.field.operation.TextFieldOperation;
import org.example.form.field.output.TextFieldListOutput;
import org.example.form.message.FieldMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TextFieldOperationTest {

    @Test
    public void getInputTipoDeCampoDeEntradaNaoEsperadoTest() {
        CustomException exception = assertThrows(CustomException.class, () -> {
           new TextFieldOperation(new FieldTemplate(), new TextFieldListInput(), false);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getError() instanceof FieldError);
        FieldError fieldError = new ObjectMapper().convertValue(exception.getError(), FieldError.class);
        assertEquals(FieldMessage.TIPO_DE_CAMPO_DE_ENTRADA_NAO_ESPERADO, fieldError.getMessage());
    }

    @Test
    public void getOutputTipoDeCampoDeSaidaNaoEsperadoTest() {
        CustomException exception = assertThrows(CustomException.class, () -> {
            new TextFieldOperation(new FieldTemplate(), new TextFieldListOutput(), true);
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
        TextFieldOperation textFieldOperation = new TextFieldOperation(fieldTemplate, new TextFieldInput(), false);
        CustomException exception = assertThrows(CustomException.class, () -> {
            textFieldOperation.errorIsRequired();
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertTrue(exception.getError() instanceof FieldError);
        FieldError fieldError = new ObjectMapper().convertValue(exception.getError(), FieldError.class);
        assertEquals(FieldMessage.CAMPO_OBRIGATORIO, fieldError.getMessage());
    }
}
