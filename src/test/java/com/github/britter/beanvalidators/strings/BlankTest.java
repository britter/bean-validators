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
package com.github.britter.beanvalidators.strings;

import static com.google.common.collect.Iterables.getLast;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
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

public class BlankTest {

    private BlankBean blankBean;
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        blankBean = new BlankBean();
    }

    @Test
    public void shouldValidateEmptyString() throws Exception {
        blankBean.blank = "";

        Set<ConstraintViolation<BlankBean>> violations = validate("blank");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        blankBean.blank = null;

        Set<ConstraintViolation<BlankBean>> violations = validate("blank");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        blankBean.blank = "  ";

        Set<ConstraintViolation<BlankBean>> violations = validate("blank");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateNonBlankString() throws Exception {
        blankBean.blank = " abcd ";

        Set<ConstraintViolation<BlankBean>> violations = validate("blank");

        assertThat(violations, hasSize(1));
        ConstraintViolation<BlankBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be blank")));
    }

    private Set<ConstraintViolation<BlankBean>> validate(String property) {
        return validator.validateProperty(blankBean, property);
    }

    private static final class BlankBean {
        @Blank
        private String blank;
    }
}
