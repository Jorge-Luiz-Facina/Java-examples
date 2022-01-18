package example.rule.domain.type;

public enum AccountType {

    AGGRESSIVE("Aggressive"),
    PASSIVE("Passive"),
    NORMAL("Normal");

    private String description;

    AccountType(String descricao) {
        this.description = descricao;
    }

    public String getdescription() {
        return description;
    }
}