package org.example.form.service;

import lombok.RequiredArgsConstructor;
import org.example.form.dto.request.FormTemplateRequest;
import org.example.form.dto.response.FieldTemplateResponse;
import org.example.form.dto.response.FormTemplateResponse;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.output.TextFieldListOutput;
import org.example.form.field.output.TextFieldOutput;
import org.example.form.repository.FormTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FormTemplateService {

    private final FormTemplateRepository repository;

    public FormTemplateResponse create(FormTemplateRequest formTemplateRequest) throws CustomException {
        FormTemplate formTemplateExist = repository.findByNameAndSystem(formTemplateRequest.getName(), formTemplateRequest.getSystem());
        if(Objects.nonNull(formTemplateExist)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "This form template already exists");
        }
        FormTemplate formTemplate = repository.save(formTemplateRequest.toEntity());
        return FormTemplateResponse.to(formTemplate);
    }

    public FormTemplateResponse getForm(String name, String system, Integer version) throws CustomException {
        FormTemplate formTemplateExist = repository.findByNameAndSystemAndVersion(name, system, version);
        if(Objects.isNull(formTemplateExist)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "This form does not exist");
        }
        return FormTemplateResponse.to(formTemplateExist);
    }

    public void newVersion(String name, String version) {
        FormTemplate formTemplate = repository.findByNameAndSystemAndMaxVersion(name, version);
        formTemplate.setVersion(formTemplate.getVersion()+1);
    }

    public FormTemplateResponse getModel() {
        FieldTemplateResponse fieldTemplateResponse1 = FieldTemplateResponse.builder().
                output(new TextFieldOutput()).
                type(FieldTypeEnum.TEXT_FIELD).
                build();
        FieldTemplateResponse fieldTemplateResponse2 = FieldTemplateResponse.builder().
                output(new TextFieldListOutput()).
                type(FieldTypeEnum.TEXT_FIELD_LIST).
                build();

        return FormTemplateResponse.builder().
                fields(Arrays.asList(fieldTemplateResponse1, fieldTemplateResponse2))
                .build();
    }
}
