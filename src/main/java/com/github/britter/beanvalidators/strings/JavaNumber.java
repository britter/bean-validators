/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.britter.beanvalidators.strings;


import com.github.britter.beanvalidators.strings.internal.JavaNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Makes sure a String contains a valid Java number. Note that this doesn't mean the given String can be parsed by the
 * various {@code parse} and {@code valueOf} methods defined on {@link Integer} etc. It just means, that the String
 * could be used to define a number in Java code. For more information see
 * <a href="https://commons.apache.org/proper/commons-lang/javadocs/api-release/index.html">{@code org.apache.commons.lang3.math.NumberUtils.isNumber(String)}</a>].
 *
 * @since 0.4
 */
@Target({METHOD, FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = JavaNumberConstraintValidator.class)
@Documented
public @interface JavaNumber {

    String message() default "{com.github.britter.beanvalidators.strings.JavaNumber.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
