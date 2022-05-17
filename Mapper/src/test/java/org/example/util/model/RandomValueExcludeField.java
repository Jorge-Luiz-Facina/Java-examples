package org.example.util.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RandomValueExcludeField {

    private String field;
    private Class outCLass;
}