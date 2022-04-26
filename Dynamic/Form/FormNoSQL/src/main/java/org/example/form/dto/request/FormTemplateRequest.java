package org.example.form.dto.request;

import lombok.Data;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.SystemTypeEnum;
import org.example.form.exception.CustomException;

@Data
public class FormTemplateRequest {

    private String name;
    private String type;
    private SystemTypeEnum system;

    public FormTemplate toEntityCreate() {
        FormTemplate formTemplate = new FormTemplate();
        formTemplate.setName(name);
        formTemplate.setType(type);
        formTemplate.setSystem(system);
        formTemplate.setVersion(1);
        return formTemplate;
    }

    public FormTemplate toEntityUpdate(FormTemplate formTemplate) {
        FormTemplate newFormTemplate = getUpdateVersion(formTemplate);
        newFormTemplate.setName(name);
        return newFormTemplate;
    }


    public static FormTemplate getUpdateVersion(FormTemplate formTemplate) {
        FormTemplate newFormTemplate = new FormTemplate();
        newFormTemplate.setName(formTemplate.getName());
        newFormTemplate.setType(formTemplate.getType());
        newFormTemplate.setSystem(formTemplate.getSystem());
        newFormTemplate.setVersion(formTemplate.getVersion() + 1);
        newFormTemplate.setFieldTemplates(formTemplate.getFieldTemplates());
        return newFormTemplate;
    }
}