package org.example.form.field.output;

import lombok.Data;
import java.util.List;

@Data
public class TextFieldListOutput {
    private List<String> values;
    private Boolean isMultiple;
}