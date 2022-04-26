package org.example.form.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
import org.example.form.message.FormMessage;
import org.example.form.repository.FormDataRepository;
import org.example.form.repository.FormTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class FormDataService {
    private final FormTemplateRepository formTemplateRepository;
    private final FormDataRepository repository;

    public FormDataResponse create(String formTemplateId, FormDataRequest formDataRequest) throws CustomException {
        FormTemplate formTemplate = formTemplateRepository.
                findById(new ObjectId(formTemplateId)).orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST,
                FormMessage.NAO_EXISTE_ESSE_FORMULARIO));

        FormData formData = new FormData();
        formDataRequest.toEntity(formTemplate, formData);
        return FormDataResponse.to(repository.save(formData));
    }

    public FormDataResponse update(String formDataId, FormDataRequest formDataRequest) throws CustomException {
        FormData formDataExists = getById(formDataId);

        formDataRequest.toEntity(formDataExists.getFormTemplate(), formDataExists);
        return FormDataResponse.to(repository.save(formDataExists));
    }

    public FormDataResponse getByIdResponse(String formDataId) throws CustomException {
        FormData formData = getById(formDataId);
        return FormDataResponse.to(formData);
    }

    private FormData getById(String formDataId) throws CustomException {
        return repository.findById(new ObjectId(formDataId)).
                orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, FormMessage.NAO_EXISTE_ESSE_FORMULARIO));
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
