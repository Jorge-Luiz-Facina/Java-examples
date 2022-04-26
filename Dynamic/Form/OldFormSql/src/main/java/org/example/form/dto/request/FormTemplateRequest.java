package org.example.form.dto.request;

import lombok.Data;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.SystemTypeEnum;
import java.util.List;

@Data
public class FormTemplateRequest {

    private String name;
    private SystemTypeEnum system;
    private List<FieldTemplateRequest> fields;

    public FormTemplate toEntity() {
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setName(name);
        formTemplate.setSystem(system);
        formTemplate.setVersion(1);
        return formTemplate;
    }
}
