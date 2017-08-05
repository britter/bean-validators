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

import static com.google.common.collect.Iterables.getLast;
import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationWrapper<T> {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    private T bean;
    private String defaultMessage;

    public ValidationWrapper(T bean, String defaultMessage) {
        this.bean = bean;
        this.defaultMessage = defaultMessage;
    }

    public Set<ConstraintViolation<T>> validate(String property) {
        return VALIDATOR.validateProperty(bean, property);
    }

    public void assertNoViolations(String property) {
        Set<ConstraintViolation<T>> violations = validate(property);

        assertThat(violations)
                .as("Expected no violations, but got some")
                .isEmpty();
    }

    public void assertViolation(String property) {
        Set<ConstraintViolation<T>> violations = validate(property);
        assertThat(violations)
                .as("Wrong violations count")
                .hasSize(1);

        ConstraintViolation<T> violation = getLast(violations);
        assertThat(violation.getMessage())
                .as("Wrong violation message")
                .isEqualTo(defaultMessage);
    }
}
