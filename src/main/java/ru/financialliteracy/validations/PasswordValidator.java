package ru.financialliteracy.validations;

import ru.financialliteracy.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = Pattern.compile(".{7,}").matcher(s);
        return matcher.matches();
    }
}