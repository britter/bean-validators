package com.github.britter.beanvalidators.file;

import com.github.britter.beanvalidators.NullAcceptingConstraintValidator;

import javax.validation.ConstraintValidatorContext;
import java.io.File;
import java.lang.annotation.Annotation;

interface StringToFileConvertingConstraintValidator<A extends Annotation> extends NullAcceptingConstraintValidator<A, String> {

    @Override
    default boolean isValidNonNullValue(final String value, final ConstraintValidatorContext context) {
        return isValidNonNullFile(new File(value), context);
    }

    boolean isValidNonNullFile(File file, ConstraintValidatorContext context);
}
