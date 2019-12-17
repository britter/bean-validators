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
package com.github.britter.beanvalidators;

import org.assertj.core.api.AbstractAssert;

import javax.validation.ConstraintViolation;
import java.util.Objects;

public final class ConstraintViolationAssert<T> extends AbstractAssert<ConstraintViolationAssert<T>, ConstraintViolation<T>> {

    public ConstraintViolationAssert(ConstraintViolation<T> actual) {
        super(actual, ConstraintViolationAssert.class);
    }

    public static <T> ConstraintViolationAssert<T> assertThat(ConstraintViolation<T> actual) {
        return new ConstraintViolationAssert<T>(actual);
    }

    public ConstraintViolationAssert<T> hasMessage(String message) {
        isNotNull();

        if (!Objects.equals(actual.getMessage(), message)) {
            failWithMessage("Expected violation's message to be <%s> but was <%s>", message, actual.getMessage());
        }

        return this;
    }
}
