package org.example.form.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.example.form.dto.request.FieldTemplateRequest;
import org.example.form.dto.request.FormTemplateRequest;
import org.example.form.dto.response.FieldTemplateResponse;
import org.example.form.dto.response.FormTemplateResponse;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.FieldTypeEnum;
import org.example.form.enums.SystemTypeEnum;
import org.example.form.exception.CustomException;
import org.example.form.field.output.TextFieldListOutput;
import org.example.form.field.output.TextFieldOutput;
import org.example.form.message.FieldMessage;
import org.example.form.message.FormMessage;
import org.example.form.repository.FormTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormTemplateService {

    private final FormTemplateRepository repository;

    public FormTemplateResponse create(FormTemplateRequest formTemplateRequest) throws CustomException {
        Optional<FormTemplate> formTemplateExist = repository.findByTypeAndSystem(formTemplateRequest.getType(), formTemplateRequest.getSystem());
        if (formTemplateExist.isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, FormMessage.ESTE_FORMULARIO_JA_EXISTE);
        }
        FormTemplate formTemplate = repository.save(formTemplateRequest.toEntityCreate());
        return FormTemplateResponse.to(formTemplate);
    }

    public FormTemplateResponse update(String formTemplateId, FormTemplateRequest formTemplateRequest) throws CustomException {
        FormTemplate formTemplateLastVersion = findByTypeAndSystemAndLastVersion(formTemplateId);
        FormTemplate formTemplate = repository.save(formTemplateRequest.toEntityUpdate(formTemplateLastVersion));
        return FormTemplateResponse.to(formTemplate);
    }

    public FormTemplateResponse createFieldTemplate(String formTemplateId, FieldTemplateRequest fieldTemplateRequest) throws CustomException {
        FormTemplate formTemplateLastVersion = findByTypeAndSystemAndLastVersion(formTemplateId);
        errorFieldPositionExists(formTemplateLastVersion, fieldTemplateRequest.getPosition());
        FormTemplate formTemplate = FormTemplateRequest.getUpdateVersion(formTemplateLastVersion);
        fieldTemplateRequest.toEntityCreate(formTemplate);
        FormTemplate formTemplateSave = repository.save(formTemplate);
        return FormTemplateResponse.to(formTemplateSave);
    }

    public FormTemplateResponse updateFieldTemplate(String formTemplateId, String fieldTemplateId, FieldTemplateRequest fieldTemplateRequest) throws CustomException {
        FormTemplate formTemplateLastVersion = findByTypeAndSystemAndLastVersion(formTemplateId);
        errorFieldUpdatePositionExists(formTemplateLastVersion, fieldTemplateId, fieldTemplateRequest.getPosition());
        FormTemplate formTemplate = FormTemplateRequest.getUpdateVersion(formTemplateLastVersion);
        FieldTemplate fieldTemplate = getFieldTemplateId(formTemplate, fieldTemplateId);
        fieldTemplateRequest.toEntityUpdate(fieldTemplate);
        FormTemplate formTemplateSave = repository.save(formTemplate);
        return FormTemplateResponse.to(formTemplateSave);
    }

    public FormTemplateResponse deleteFieldTemplate(String formTemplateId, String fieldTemplateId) throws CustomException {
        FormTemplate formTemplateLastVersion = findByTypeAndSystemAndLastVersion(formTemplateId);
        formTemplateLastVersion.getFieldTemplates().removeIf(fieldTemplate -> fieldTemplate.getId().equals(fieldTemplateId));
        FormTemplate formTemplate = repository.save(FormTemplateRequest.getUpdateVersion(formTemplateLastVersion));
        return FormTemplateResponse.to(formTemplate);
    }

    public FormTemplateResponse getForm(String type, SystemTypeEnum system, Integer version) throws CustomException {
        FormTemplate formTemplateExist = findByTypeAndSystemAndVersion(type, system, version).
                orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, FormMessage.NAO_EXISTE_ESSE_FORMULARIO));

        return FormTemplateResponse.to(formTemplateExist);
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

    private FormTemplate findByTypeAndSystemAndLastVersion(String formTemplateId) throws CustomException {
        FormTemplate formTemplateExist = repository.findById(new ObjectId(formTemplateId)).
                orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, FormMessage.NAO_EXISTE_ESSE_FORMULARIO));

        return findByTypeAndSystemAndVersion(formTemplateExist.getType(), formTemplateExist.getSystem(), 0).get();
    }

    private Optional<FormTemplate> findByTypeAndSystemAndVersion(String type, SystemTypeEnum system, Integer version) {
        if (version.equals(0)) {
            return repository.findTopByTypeAndSystemOrderByVersionDesc(type, system);
        } else {
            return repository.findByTypeAndSystemAndVersion(type, system, version);
        }
    }

    private void errorFieldPositionExists(FormTemplate formTemplate, Integer position) throws CustomException {
        Optional<FieldTemplate> fieldTemplate = formTemplate.getFieldTemplates().stream().
                filter(_fieldTemplate -> _fieldTemplate.getPosition().equals(position)).findAny();
        if (fieldTemplate.isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, FieldMessage.JA_EXISTE_UM_CAMPO_COM_ESSA_POSICAO);
        }
    }

    private FieldTemplate getFieldTemplateId(FormTemplate formTemplate, String fieldTemplateId) throws CustomException {
        Optional<FieldTemplate> fieldTemplate = formTemplate.getFieldTemplates().stream().
                filter(_fieldTemplate -> _fieldTemplate.getId().equals(fieldTemplateId)).findAny();
        if (fieldTemplate.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, FieldMessage.O_CAMPOS_QUE_FOI_INFORMADO_NAO_EXISTE);
        }
        return fieldTemplate.get();
    }

    private void errorFieldUpdatePositionExists(FormTemplate formTemplate, String id, Integer position) throws CustomException {
        Optional<FieldTemplate> fieldTemplate = formTemplate.getFieldTemplates().stream().
                filter(_fieldTemplate -> _fieldTemplate.getPosition().equals(position)).findAny();
        if (fieldTemplate.isPresent() && !fieldTemplate.get().getId().equals(id)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, FieldMessage.JA_EXISTE_UM_CAMPO_COM_ESSA_POSICAO);
        }
    }
}