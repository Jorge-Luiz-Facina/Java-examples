package org.example.form.dto.request;

import lombok.Data;
import org.example.form.entity.FormTemplate;
import org.example.form.exception.CustomException;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FormTemplateRequest {

    private String name;
    private String system;
    private List<FieldTemplateRequest> fields;

    public FormTemplate toEntity() throws CustomException {
        fieldsIsEmpty();
        fieldsPositionIsError();
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setName(name);
        formTemplate.setSystem(system);
        formTemplate.setVersion(1);
        formTemplate.setFieldTemplates(fields.stream().
                map(fieldTemplateRequest ->
                    fieldTemplateRequest.toEntity(formTemplate)).
                collect(Collectors.toList()));
        return formTemplate;
    }

    private void fieldsIsEmpty() throws CustomException {
        if(fields.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Fields is empty");
        }
    }

    private void fieldsPositionIsError() throws CustomException {
        List <Integer> positionsField =  fields.stream().
                map(fieldTemplateRequest -> fieldTemplateRequest.getPosition()).collect(Collectors.toList());
        List <Integer> positions = new ArrayList<>();
        int position = 0;

        for (Integer fieldRequestPosition : positionsField) {
            positions.add(position);
            position++;
        }

        if(!positionsField.containsAll(positions)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "fields position is error");
        }
    }
}
