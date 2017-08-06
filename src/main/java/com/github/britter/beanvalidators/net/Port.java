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
package com.github.britter.beanvalidators.net;


import com.github.britter.beanvalidators.net.internal.PortIntegerConstraintValidator;
import com.github.britter.beanvalidators.net.internal.PortStringConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Makes sure a String, int or Integer represents a valid <a href="https://en.wikipedia.org/wiki/Port_(computer_networking)">port</a>
 * number.
 *
 * <p>A port number is a 16-bit unsigned integer, thus ranging from 0 to 65535. {@code @Port} can be applied to String,
 * ints and Integers. When applied to any other type, a {@link javax.validation.ValidationException} will be thrown.</p>
 *
 * @since 0.3
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {
        PortIntegerConstraintValidator.class,
        PortStringConstraintValidator.class
})
@Documented
public @interface Port {

    String message() default "{com.github.britter.beanvalidators.net.Port.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
