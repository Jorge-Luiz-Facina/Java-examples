package org.example.form.field.input;

import lombok.Data;
import java.util.List;

@Data
public class TextFieldListInput {
    private List<String> values;

    public Boolean requiredOk() {
        return !values.isEmpty();
    }

    public Boolean multipleOk() {
        return values.size() > 1;
    }
}