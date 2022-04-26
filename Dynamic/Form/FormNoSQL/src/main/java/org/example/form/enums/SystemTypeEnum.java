package org.example.form.enums;

public enum SystemTypeEnum {
    APPLICATION1("Application1");

    private String description;

    SystemTypeEnum(String description) {
        this.description =  description;
    }

    public String getDescription() {
        return description;
    }
}