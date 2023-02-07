package ru.financialliteracy.validations;

import ru.financialliteracy.annotations.ValidAnswer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidAnswerValidator implements ConstraintValidator<ValidAnswer, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = Pattern.compile("[абвАБВ]").matcher(s.trim());
        return matcher.matches();
    }
}