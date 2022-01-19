package example.hibernatevalidator;


import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@RequiredArgsConstructor
public class UserValidator {

    private final Validator validator;

    public void validate(User user) throws UserFieldException {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            StringBuilder message = new StringBuilder().append("Violation fields:");
            for (ConstraintViolation<User> violation : violations) {
                message.append(" ").append(violation.getMessage());
            }
             throw new UserFieldException(message.toString());
        }
    }

}