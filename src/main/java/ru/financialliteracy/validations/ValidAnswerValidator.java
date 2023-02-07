package ru.financialliteracy.validations;

import ru.financialliteracy.annotations.ValidAnswer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAnswerValidator implements ConstraintValidator<ValidAnswer, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return "1".equals(s) || "2".equals(s) || "3".equals(s);
    }
}
