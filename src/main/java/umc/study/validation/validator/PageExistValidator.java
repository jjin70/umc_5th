package umc.study.validation.validator;

import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.validation.annotation.ExistPage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PageExistValidator implements ConstraintValidator<ExistPage, Integer> {
    @Override
    public void initialize(ExistPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = value > 0;
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_FOUND.toString()).addConstraintViolation();
        }
        return isValid;
    }
}