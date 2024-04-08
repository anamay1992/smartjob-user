package cl.smartjob.api.user.infrastructure.adapter.output.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class PasswordValidate implements ConstraintValidator<ValidPassword, String> {

    @Value("${pattern.password.regex}")
    private String passwordRegex;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }
        return value.matches(passwordRegex);
    }

}
