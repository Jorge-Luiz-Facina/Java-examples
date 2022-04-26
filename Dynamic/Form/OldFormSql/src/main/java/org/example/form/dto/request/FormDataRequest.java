package org.example.form.dto.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormData;
import org.example.form.entity.FormTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import org.example.form.field.rule.AbstractFieldRule;
import org.example.form.field.rule.TextFieldListRule;
import org.example.form.field.rule.TextFieldRule;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class FormDataRequest {

    private String name;
    private String system;
    private Integer version;
    private List<FieldDataRequest> fieldsData;

    @SneakyThrows
    public FormData toEntity(FormTemplate formTemplate) {
        List<AbstractFieldRule> abstractFieldRules = Arrays.asList(new TextFieldRule(), new TextFieldListRule());
        FormData formData = new FormData();
        formData.setFormTemplate(formTemplate);
        formData.setFieldsData(new ArrayList<>());

        List<FieldTemplate> fieldTemplatesIsActive = getFieldTemplatesIsActive(formTemplate);

        for(FieldTemplate fieldTemplate : fieldTemplatesIsActive) {
            Optional<FieldDataRequest> fieldDataRequest = fieldsData.
                    stream().
                    filter(_fieldDataRequest ->
                            _fieldDataRequest.getPosition().equals(fieldTemplate.getPosition())).findFirst();


            errorFieldVoid(fieldTemplate, fieldDataRequest);
            for(AbstractFieldRule abstractFieldRule : abstractFieldRules) {
                if(abstractFieldRule.check(fieldTemplate, fieldDataRequest)) {

                    String value = new ObjectMapper().
                            writeValueAsString(abstractFieldRule.execute(fieldTemplate, fieldDataRequest.get()));
                    formData.getFieldsData().add(fieldDataRequest.get().toEntity(formData, fieldTemplate,value));
                }
            }
        }
        return formData;
    }

    private void errorFieldVoid(FieldTemplate fieldTemplate, Optional<FieldDataRequest> fieldDataRequest) throws CustomException {
        if(fieldDataRequest.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    FieldError.to(fieldTemplate, null, null, "O campo tem que existir"));
        }
    }

    private List<FieldTemplate> getFieldTemplatesIsActive(FormTemplate formTemplate){
        return formTemplate.getFieldTemplates().stream().
                filter(fieldTemplate -> fieldTemplate.getIsActive().equals(true)).
                collect(Collectors.toList());
    }
}
