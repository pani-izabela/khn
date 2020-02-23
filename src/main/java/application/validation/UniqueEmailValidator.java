package application.validation;

import application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    AppUserService appUserService;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !appUserService.checkAppUserByEmail(value);
    }
}
