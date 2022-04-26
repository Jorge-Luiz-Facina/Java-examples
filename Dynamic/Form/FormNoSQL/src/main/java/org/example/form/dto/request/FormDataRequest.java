package org.example.form.dto.request;

import lombok.Data;
import org.example.form.entity.FieldTemplate;
import org.example.form.entity.FormData;
import org.example.form.entity.FormTemplate;
import org.example.form.exception.CustomException;
import org.example.form.field.error.FieldError;
import org.example.form.field.rule.AbstractFieldRule;
import org.example.form.field.rule.TextFieldListRule;
import org.example.form.field.rule.TextFieldRule;
import org.example.form.message.FieldMessage;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class FormDataRequest {

    @NotEmpty
    private List<@Valid FieldDataRequest> fieldsData;

    public void toEntity(FormTemplate formTemplate, FormData formData) throws CustomException {
        List<AbstractFieldRule> abstractFieldRules = Arrays.asList(new TextFieldRule(), new TextFieldListRule());
        formData.setFormTemplate(formTemplate);
        formData.setFieldsData(new ArrayList<>());

        List<FieldTemplate> fieldTemplatesIsActive = getFieldTemplatesIsActive(formTemplate);

        for (FieldTemplate fieldTemplate : fieldTemplatesIsActive) {
            FieldDataRequest fieldDataRequest = fieldsData.
                    stream().
                    filter(_fieldDataRequest ->
                            _fieldDataRequest.getFieldTemplateId().equals(fieldTemplate.getId())).findFirst().get();

            for (AbstractFieldRule abstractFieldRule : abstractFieldRules) {
                if (abstractFieldRule.check(fieldTemplate)) {
                    Object value = abstractFieldRule.execute(fieldTemplate, fieldDataRequest);
                    formData.getFieldsData().add(fieldDataRequest.toEntity(fieldTemplate, value));
                }
            }
        }
    }

    private List<FieldTemplate> getFieldTemplatesIsActive(FormTemplate formTemplate) {
        return formTemplate.getFieldTemplates().stream().
                filter(fieldTemplate -> fieldTemplate.getIsActive().equals(true)).
                collect(Collectors.toList());
    }

}
