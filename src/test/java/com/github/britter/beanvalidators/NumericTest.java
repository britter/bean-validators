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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class NumericTest {

    private Validator validator;
    private NumericBean numericBean;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        numericBean = new NumericBean();
    }

    @Test
    public void shouldValidateNumericString() throws Exception {
        numericBean.numeric = "123456";

        Set<ConstraintViolation<NumericBean>> violations = validate("numeric");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        numericBean.numeric = null;

        Set<ConstraintViolation<NumericBean>> violations = validate("numeric");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        numericBean.numeric = " ";

        Set<ConstraintViolation<NumericBean>> violations = validate("numeric");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateNonNumericString() throws Exception {
        numericBean.numeric = "abc";

        Set<ConstraintViolation<NumericBean>> violations = validate("numeric");

        assertThat(violations, hasSize(1));
        ConstraintViolation<NumericBean> violation = getFirst(violations);
        assertThat(violation.getMessage(), is("Numeric field is not valid"));
    }

    private Set<ConstraintViolation<NumericBean>> validate(String property) {
        return validator.validateProperty(numericBean, property);
    }

    private static <T> T getFirst(Set<T> set) {
        return set.iterator().next();
    }

    private static final class NumericBean {
        @Numeric
        private String numeric;
    }
}
