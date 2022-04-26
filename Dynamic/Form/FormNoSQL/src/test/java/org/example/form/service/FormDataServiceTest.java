package org.example.form.service;

import org.example.form.dto.request.FormDataRequest;
import org.example.form.exception.CustomException;
import org.example.form.message.FormMessage;
import org.example.form.repository.FormDataRepository;
import org.example.form.repository.FormTemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FormDataServiceTest {

    @InjectMocks
    public FormDataService formDataService;

    @Mock
    FormTemplateRepository formTemplateRepository;

    @Mock
    FormDataRepository formDataRepository;

    @Test
    public void createNaoExisteEsseFormularioTest() {
        Mockito.lenient().when(formTemplateRepository.findById(any())).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            formDataService.create("1263102014023f013b1c3310", new FormDataRequest());
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FormMessage.NAO_EXISTE_ESSE_FORMULARIO, exception.getError());
    }

    @Test
    public void updateNaoExisteEsseFormularioTest() {
        Mockito.lenient().when(formDataRepository.findById(any())).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            formDataService.update("1263102014023f013b1c3310", new FormDataRequest());
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FormMessage.NAO_EXISTE_ESSE_FORMULARIO, exception.getError());
    }

    @Test
    public void getByIdResponseNaoExisteEsseFormularioTest() {
        Mockito.lenient().when(formTemplateRepository.findById(any())).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            formDataService.getByIdResponse("1263102014023f013b1c3310");
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FormMessage.NAO_EXISTE_ESSE_FORMULARIO, exception.getError());
    }
}
