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

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationWrapper<T> {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    private T bean;

    public ValidationWrapper(T bean) {
        this.bean = bean;
    }

    public Set<ConstraintViolation<T>> validate(String property) {
        return VALIDATOR.validateProperty(bean, property);
    }
    
    public void assertNoViolations(String property) {
        Set<ConstraintViolation<T>> violations = validate(property);
        assertThat("Expected no violations, but got some", violations, is(empty()));
    }
}
