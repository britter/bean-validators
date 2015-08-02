/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.britter.beanvalidators.file;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.io.File;
import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;

abstract class AbstractFileConstraintValidator<A extends Annotation> implements ConstraintValidator<A, Object> {

    private Class<? extends Annotation> annotationType;

    @Override
    public void initialize(final A constraintAnnotation) {
        this.annotationType = constraintAnnotation.annotationType();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value instanceof File) {
            return isValid((File) value, context);
        } else if (value instanceof String) {
            String str = (String) value;
            return StringUtils.isBlank(str) || isValid(new File(str), context);
        } else {
            throw new ValidationException("@" + annotationType.getSimpleName()
                    + " can not be applied to instances of " + value.getClass());
        }
    }

    public abstract boolean isValid(final File value, final ConstraintValidatorContext context);
}
