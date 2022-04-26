package org.example.form.enums;

public enum FieldTypeEnum {
    TEXT_FIELD("TextField"),
    TEXT_FIELD_LIST("TextFieldList");

    private String description;

    FieldTypeEnum(String description) {
        this.description =  description;
    }

    public String getDescription() {
        return description;
    }
}