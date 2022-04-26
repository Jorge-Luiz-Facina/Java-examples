package org.example.form.service;

import org.example.form.dto.request.FieldTemplateRequest;
import org.example.form.dto.request.FormTemplateRequest;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.SystemTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.message.FieldMessage;
import org.example.form.message.FormMessage;
import org.example.form.repository.FormTemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FormTemplateServiceTest {

    @InjectMocks
    public FormTemplateService formTemplateService;

    @Mock
    FormTemplateRepository formTemplateRepository;

    @Test
    public void createFormularioJaExisteTest() {
        FormTemplate formTemplate = new FormTemplate();
        Mockito.doReturn(Optional.of(formTemplate)).
                when(formTemplateRepository).findByTypeAndSystem(any(), any());
        FormTemplateRequest formTemplateRequest = new FormTemplateRequest();
        formTemplateRequest.setSystem(SystemTypeEnum.APPLICATION1);
        formTemplateRequest.setName("TESTE");
        formTemplateRequest.setName("TESTES");

         CustomException exception = assertThrows(CustomException.class, () -> {
             formTemplateService.create(formTemplateRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FormMessage.ESTE_FORMULARIO_JA_EXISTE, exception.getError());
    }

    @Test
    public void getFormFormularioNaoExisteTest() {
        Mockito.lenient().when(formTemplateRepository.findTopByTypeAndSystemOrderByVersionDesc(any(), any())).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            formTemplateService.getForm("", SystemTypeEnum.APPLICATION1, 0);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FormMessage.NAO_EXISTE_ESSE_FORMULARIO, exception.getError());
    }

    @Test
    public void updateFormularioNaoExisteTest() {
        Mockito.lenient().when(formTemplateRepository.findById(any())).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            formTemplateService.update("1263102014023f013b1c3310", new FormTemplateRequest());
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FormMessage.NAO_EXISTE_ESSE_FORMULARIO, exception.getError());
    }

    @Test
    public void createFieldTemplateJaExisteUmCampoComEssaPosicaoTest() {
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setId("1263102014023f013b1c3310");

        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setPosition(1);
        formTemplate.setFieldTemplates(Arrays.asList(fieldTemplate));
        Mockito.doReturn(Optional.of(formTemplate)).
                when(formTemplateRepository).findById(any());
        Mockito.doReturn(Optional.of(formTemplate)).
                when(formTemplateRepository).findTopByTypeAndSystemOrderByVersionDesc(any(), any());
        FieldTemplateRequest fieldTemplateRequest = new FieldTemplateRequest();
        fieldTemplateRequest.setPosition(1);

        CustomException exception = assertThrows(CustomException.class, () -> {
            formTemplateService.createFieldTemplate("1263102014023f013b1c3310", fieldTemplateRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FieldMessage.JA_EXISTE_UM_CAMPO_COM_ESSA_POSICAO, exception.getError());
    }

    @Test
    public void updateFieldTemplateJaExisteUmCampoComEssaPosicaoTest() {
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setId("1263102014023f013b1c3310");

        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setId("2263102014023f013b1c3310");
        fieldTemplate.setPosition(1);
        formTemplate.setFieldTemplates(Arrays.asList(fieldTemplate));
        Mockito.doReturn(Optional.of(formTemplate)).
                when(formTemplateRepository).findById(any());
        Mockito.doReturn(Optional.of(formTemplate)).
                when(formTemplateRepository).findTopByTypeAndSystemOrderByVersionDesc(any(), any());
        FieldTemplateRequest fieldTemplateRequest = new FieldTemplateRequest();
        fieldTemplateRequest.setPosition(1);

        CustomException exception = assertThrows(CustomException.class, () -> {
            formTemplateService.updateFieldTemplate("1263102014023f013b1c3310", "3263102014023f013b1c3310", fieldTemplateRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FieldMessage.JA_EXISTE_UM_CAMPO_COM_ESSA_POSICAO, exception.getError());
    }

    @Test
    public void updateFieldTemplateOCampoQueFoiInformadoNaoExisteTest() {
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setId("1263102014023f013b1c3310");
        formTemplate.setVersion(1);
        FieldTemplate fieldTemplate = new FieldTemplate();
        fieldTemplate.setId("2263102014023f013b1c3310");
        fieldTemplate.setPosition(1);
        formTemplate.setFieldTemplates(Arrays.asList(fieldTemplate));
        Mockito.doReturn(Optional.of(formTemplate)).
                when(formTemplateRepository).findById(any());
        Mockito.doReturn(Optional.of(formTemplate)).
                when(formTemplateRepository).findTopByTypeAndSystemOrderByVersionDesc(any(), any());
        FieldTemplateRequest fieldTemplateRequest = new FieldTemplateRequest();
        fieldTemplateRequest.setPosition(2);

        CustomException exception = assertThrows(CustomException.class, () -> {
            formTemplateService.updateFieldTemplate("1263102014023f013b1c3310", "3263102014023f013b1c3310", fieldTemplateRequest);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        assertEquals(FieldMessage.O_CAMPOS_QUE_FOI_INFORMADO_NAO_EXISTE, exception.getError());
    }
}
