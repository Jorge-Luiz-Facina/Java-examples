package org.example.form.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.form.entity.FormTemplate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FormTemplateResponse {

    private String name;
    private String system;
    private List<FieldTemplateResponse> fields;

    public static FormTemplateResponse to(FormTemplate formTemplate) {
       return FormTemplateResponse.builder().
               name(formTemplate.getName()).
               system(formTemplate.getSystem()).
               fields(formTemplate.getFieldTemplates().stream().
                       map(field -> FieldTemplateResponse.to(field)).collect(Collectors.toList())).
               build();
    }
}
