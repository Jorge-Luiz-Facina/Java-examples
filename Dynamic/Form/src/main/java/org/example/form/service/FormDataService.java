package org.example.form.service;

import lombok.RequiredArgsConstructor;
import org.example.form.dto.request.FormDataRequest;
import org.example.form.dto.response.FieldDataResponse;
import org.example.form.dto.response.FormDataResponse;
import org.example.form.entity.FormData;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.input.TextFieldInput;
import org.example.form.field.input.TextFieldListInput;
import org.example.form.field.output.TextFieldListOutput;
import org.example.form.field.output.TextFieldOutput;
import org.example.form.repository.FormDataRepository;
import org.example.form.repository.FormTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormDataService {
    private final FormTemplateRepository formTemplateRepository;
    private final FormDataRepository repository;

    public FormDataResponse create(FormDataRequest formDataRequest) {
        FormTemplate formTemplate = formTemplateRepository.
                findByNameAndSystemAndVersion(formDataRequest.getName(), formDataRequest.getSystem(),
                        formDataRequest.getVersion());

        FormData formData = formDataRequest.toEntity(formTemplate);
        return FormDataResponse.to(repository.save(formData));
    }

    public List<FormDataResponse> getAll() {
        return repository.findAll().stream().map(formData -> FormDataResponse.to(formData)).collect(Collectors.toList());
    }

    public FormDataResponse getById(Long id) throws CustomException {
        FormData formData = repository.findById(id).
                orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "This form does not exist"));

        return FormDataResponse.to(formData);
    }

    public FormDataResponse getModel() {
        FieldDataResponse fieldDataResponse1 = FieldDataResponse.builder().
                output(new TextFieldOutput()).
                input(new TextFieldInput()).
                type(FieldTypeEnum.TEXT_FIELD).
                build();

        FieldDataResponse fieldDataResponse2 = FieldDataResponse.builder().
                output(new TextFieldListOutput()).
                input(new TextFieldListInput()).
                type(FieldTypeEnum.TEXT_FIELD_LIST).
                build();

        return FormDataResponse.builder().
                fields(Arrays.asList(fieldDataResponse1, fieldDataResponse2))
                .build();
    }
}
