package umc.study.validation.annotation;

import umc.study.validation.validator.PageExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistPage {

    String message() default "페이지 번호를 확인해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}