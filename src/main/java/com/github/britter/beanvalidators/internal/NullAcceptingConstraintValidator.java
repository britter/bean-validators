/*
 * Copyright 2017 Benedikt Ritter
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
package com.github.britter.beanvalidators.internal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public interface NullAcceptingConstraintValidator<A extends Annotation, T> extends ConstraintValidator<A, T> {

    @Override
    default void initialize(final A constraintAnnotation) {
    }

    @Override
    default boolean isValid(final T value, final ConstraintValidatorContext context) {
        // Don't validate null, since these are validated by @NotNull
        return value == null || isValidNonNullValue(value, context);
    }

    boolean isValidNonNullValue(final T value, final ConstraintValidatorContext context);
}
