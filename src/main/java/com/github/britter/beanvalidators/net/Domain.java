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
package com.github.britter.beanvalidators.net;

import com.github.britter.beanvalidators.net.internal.DomainConstraintValidator;

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
 * Makes sure a string represents a valid domain.
 *
 * <p>Note that the list of valid top level domains is changes frequently. Since
 * this validator uses <a href="https://commons.apache.org/proper/commons-validator/apidocs/index.html">{@code org.apache.commons.validator.routines.DomainValidator}</a>
 * under the hood, there may be domains which
 * are actually valid reported as invalid, because the Apache Commons Validator TLP list is not up to date.</p>
 *
 * @since 0.3
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DomainConstraintValidator.class)
@Documented
public @interface Domain {

    String message() default "{com.github.britter.beanvalidators.net.Domain.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
