package application.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
// Ogólnie to nie trzeba takich czarów robić :D
// Można sprawdzić tylko czy taki email istnieje w bazie
public @interface UniqueEmail {
    public String message() default "There is already user with this email!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
