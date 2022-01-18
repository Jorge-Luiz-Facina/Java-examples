package example.rule.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User{

    private String name;
    private Account account;
    private int age;
    private String email;
    private String cpf;
}
