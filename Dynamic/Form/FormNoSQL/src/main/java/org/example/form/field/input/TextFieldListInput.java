package org.example.form.field.input;

import lombok.Data;
import java.util.List;
import java.util.Objects;

@Data
public class TextFieldListInput {
    private List<String> values;

    public Boolean requiredOk() {
        return Objects.nonNull(values) && !values.isEmpty();
    }

    public Boolean multipleOk() {
        return values.size() > 1;
    }
}