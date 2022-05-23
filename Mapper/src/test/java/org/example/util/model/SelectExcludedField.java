package org.example.util.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SelectExcludedField {
    private Class typeClass;
    private List<String> fields;
}
