package cl.smartjob.api.user.infrastructure.adapter.output.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidate.class)
public @interface ValidPassword {

    String message() default "La contrasena debe tener un rango de 8 a 14 caracteres y no debe tener caracteres especiales.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
