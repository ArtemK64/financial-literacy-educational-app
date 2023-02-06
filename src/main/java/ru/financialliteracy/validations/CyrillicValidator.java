package ru.financialliteracy.validations;

import ru.financialliteracy.annotations.Cyrillic;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CyrillicValidator implements ConstraintValidator<Cyrillic, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = Pattern.compile("[а-яА-Я]").matcher(s);
        return matcher.matches();
    }
}
