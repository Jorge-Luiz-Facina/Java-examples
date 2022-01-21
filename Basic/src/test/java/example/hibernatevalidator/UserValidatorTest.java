package example.hibernatevalidator;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;

    @Spy
    private Validator validator;

    @Test
    public void allFieldsError()  {
        User user = getUserError();
        List<String> erros = allErros();
        allFieldErrorContains(user, erros);
    }

    private void allFieldErrorContains(User user, List<String> errors){
        Mockito.when(validator.validate(getUserError())).thenReturn(getViolations(getUserError()));
        Exception expectedEx = Assertions.assertThrows(UserFieldException.class, () ->
                userValidator.validate(user)
        );
        for(String error : errors) {
            assertThat(expectedEx.getMessage(), CoreMatchers.containsString(error));
        }
    }

    private Set<ConstraintViolation<User>> getViolations(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(user);
    }

    private User getUserError(){
        return User.builder().
               description("test").
                age(1).
                email("email").
                cpf("7894").
                build();
    }

    private List<String> allErros(){
        return Arrays.asList("Name - cannot be empty",
                "Description - Number of characters should be in between ", "Age - should not be less than 18" ,
                "Email - should be valid", "CPF - should be valid");
    }
}