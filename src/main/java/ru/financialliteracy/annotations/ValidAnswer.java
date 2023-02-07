package ru.financialliteracy.annotations;

import ru.financialliteracy.validations.ValidAnswerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAnswerValidator.class)
@Documented
public @interface ValidAnswer {
    String message() default "Ответ может быть только «А», «Б» или «В»";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
