package org.example.form.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.form.entity.FormData;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FormDataResponse {

    private Long id;
    private String name;
    private String system;
    private Integer version;
    private List<FieldDataResponse> fields;

    public static FormDataResponse to(FormData formData) {
        return FormDataResponse.builder().
                id(formData.getId()).
                name(formData.getFormTemplate().getName()).
                system(formData.getFormTemplate().getSystem()).
                version(formData.getFormTemplate().getVersion()).
                fields(formData.getFieldsData().stream().
                        map(fieldData -> FieldDataResponse.to(fieldData)).collect(Collectors.toList())).
                build();
    }
}
