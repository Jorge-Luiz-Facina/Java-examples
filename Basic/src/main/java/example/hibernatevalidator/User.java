package example.hibernatevalidator;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

@Data
@Builder
public class User{

    @NotNull(message = "Name - cannot be empty")
    private String name;

    @Size(min = 10, max = 100, message = "Description - Number of characters should be in between {min} and {max}")
    private String description;

    @Min(value = 18, message = "Age - should not be less than 18")
    private int age;

    @Email(message = "Email - should be valid")
    private String email;

    @CPF(message = "CPF - should be valid")
    private String cpf;
}
