package org.example.form.field.input;

import lombok.Data;

import java.util.Objects;

@Data
public class TextFieldInput {
    public String value;

    public Boolean requiredOk() {
        return Objects.nonNull(value) && !value.isEmpty() && !value.isBlank();
    }
}
