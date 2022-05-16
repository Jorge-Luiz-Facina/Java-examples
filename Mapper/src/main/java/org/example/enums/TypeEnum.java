package org.example.enums;

import lombok.Getter;

public enum TypeEnum {
    TESTE1("Teste1"),
    TESTE2("Teste2");

    @Getter
    private  String description;

    TypeEnum(String descricao) {
        this.description = descricao;
    }

    public String getValue() {
        return this.name();
    }
}
