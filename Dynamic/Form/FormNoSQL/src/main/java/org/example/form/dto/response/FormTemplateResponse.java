package org.example.form.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.form.entity.FormTemplate;
import org.example.form.enums.SystemTypeEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
public class FormTemplateResponse {

    private String id;
    private String name;
    private String type;
    private Integer version;
    private SystemTypeEnum system;
    private List<FieldTemplateResponse> fields;

    public static FormTemplateResponse to(FormTemplate formTemplate) {
       return FormTemplateResponse.builder().
               id(formTemplate.getId()).
               name(formTemplate.getName()).
               type(formTemplate.getType()).
               version(formTemplate.getVersion()).
               system(formTemplate.getSystem()).
               fields(Objects.isNull(formTemplate.getFieldTemplates()) ? new ArrayList<>() : formTemplate.getFieldTemplates().stream().
                       map(field -> FieldTemplateResponse.to(field)).collect(Collectors.toList())).
               build();
    }
}
