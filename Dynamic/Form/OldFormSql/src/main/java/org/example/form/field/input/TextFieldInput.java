package org.example.form.field.input;

import lombok.Data;

@Data
public class TextFieldInput {
    public String value;

    public Boolean requiredOk() {
        return !value.isBlank();
    }

}
