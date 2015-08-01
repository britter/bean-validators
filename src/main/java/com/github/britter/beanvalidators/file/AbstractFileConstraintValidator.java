package com.github.britter.beanvalidators.file;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.io.File;
import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;

abstract class AbstractFileConstraintValidator<A extends Annotation> implements ConstraintValidator<A, Object> {

    private Class<? extends Annotation> constraintAnnotation;

    @Override
    public void initialize(A constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation.annotationType();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof File) {
            return isValid((File) value, context);
        } else if (value instanceof String) {
            String str = (String) value;
            return StringUtils.isBlank(str) || isValid(new File(str), context);
        } else {
            throw new ValidationException("@" + constraintAnnotation.getSimpleName()
                    + " can not be applied to instances of " + value.getClass());
        }
    }

    public abstract boolean isValid(File value, ConstraintValidatorContext context);
}
