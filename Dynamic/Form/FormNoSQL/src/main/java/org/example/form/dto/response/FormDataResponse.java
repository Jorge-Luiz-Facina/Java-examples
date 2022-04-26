package org.example.form.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.form.entity.FieldData;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormData;
import org.example.form.enums.SystemTypeEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FormDataResponse {
    private String id;
    private String name;
    private SystemTypeEnum system;
    private Integer version;
    private List<FieldDataResponse> fields;

    public static FormDataResponse to(FormData formData) {
        FormDataResponse formDataResponse = FormDataResponse.builder().
                id(formData.getId()).
                name(formData.getFormTemplate().getName()).
                system(formData.getFormTemplate().getSystem()).
                version(formData.getFormTemplate().getVersion()).
                build();
        setFields(formData, formDataResponse);

        return formDataResponse;
    }

    private static void ordeById(FormData formData) {
        formData.getFieldsData().sort(Comparator.comparing(FieldData::getFieldTemplateId).reversed());
        formData.getFormTemplate().getFieldTemplates().sort(Comparator.comparing(FieldTemplate::getId));
    }

    private static List<FieldTemplate> getFieldIsActive(FormData formData) {
        return formData.getFormTemplate().getFieldTemplates().stream().
                filter(fieldTemplate -> fieldTemplate.getIsActive()).collect(Collectors.toList());
    }

    private static void setFields(FormData formData, FormDataResponse formDataResponse) {
        formData.getFormTemplate().setFieldTemplates(getFieldIsActive(formData));
        ordeById(formData);
        formDataResponse.setFields(new ArrayList<>());
        for (int i = 0; i < formData.getFieldsData().size(); i++) {
            formDataResponse.getFields().add(
                    FieldDataResponse.to(formData.getFormTemplate().getFieldTemplates().get(i),
                            formData.getFieldsData().get(i)));
        }
    }
}


