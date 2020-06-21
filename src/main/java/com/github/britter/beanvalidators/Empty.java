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
package com.github.britter.beanvalidators;

import com.github.britter.beanvalidators.internal.EmptyArrayConstraintValidator;
import com.github.britter.beanvalidators.internal.EmptyCollectionConstraintValidator;
import com.github.britter.beanvalidators.internal.EmptyMapConstraintValidator;
import com.github.britter.beanvalidators.internal.EmptyOptionalConstraintValidator;
import com.github.britter.beanvalidators.internal.EmptyStringConstraintValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Opposite of {@code org.hibernate.validator.constraints.NotEmpty}
 *
 * <p>Works on String, Collections, Maps, arrays and Optionals. If any other type is annotated with @Empty
 * a {@link javax.validation.ValidationException} will be thrown.</p>
 */
@Target({METHOD, FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {
        EmptyArrayConstraintValidator.class,
        EmptyCollectionConstraintValidator.class,
        EmptyMapConstraintValidator.class,
        EmptyOptionalConstraintValidator.class,
        EmptyStringConstraintValidator.class
})
@Documented
public @interface Empty {

    String message() default "{com.github.britter.beanvalidators.Empty.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
