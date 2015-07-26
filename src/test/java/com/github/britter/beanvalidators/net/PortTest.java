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

import static com.google.common.collect.Iterables.getLast;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class PortTest {

    private Validator validator;
    private PortBean portBean;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        portBean = new PortBean();
    }

    // String

    @Test
    public void shouldValidateNullString() throws Exception {
        portBean.portString = null;

        Set<ConstraintViolation<PortBean>> violations = validate("portString");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        portBean.portString = " ";

        Set<ConstraintViolation<PortBean>> violations = validate("portString");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateRandomString() throws Exception {
        portBean.portString = "abcd";

        Set<ConstraintViolation<PortBean>> violations = validate("portString");

        assertThat(violations, hasSize(1));
        ConstraintViolation<PortBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be a port number")));
    }

    @Test
    public void shouldValidateValidPortString() throws Exception {
        portBean.portString = "8080";

        Set<ConstraintViolation<PortBean>> violations = validate("portString");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateNegativePortString() throws Exception {
        portBean.portString = "-8080";

        Set<ConstraintViolation<PortBean>> violations = validate("portString");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void shouldNotValidateInvalidPortString() throws Exception {
        portBean.portString = "65537";

        Set<ConstraintViolation<PortBean>> violations = validate("portString");

        assertThat(violations, hasSize(1));
    }

    // int

    @Test
    public void shouldValidateValidPortInt() throws Exception {
        portBean.portInt = 8080;

        Set<ConstraintViolation<PortBean>> violations = validate("portInt");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateNegativePortInt() throws Exception {
        portBean.portInt = -8080;

        Set<ConstraintViolation<PortBean>> violations = validate("portInt");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void shouldNotValidateInvalidPortInt() throws Exception {
        portBean.portInt = 65537;

        Set<ConstraintViolation<PortBean>> violations = validate("portInt");

        assertThat(violations, hasSize(1));
    }

    // Integer
    @Test
    public void shouldValidateNullPortInteger() throws Exception {
        portBean.portInteger = null;

        Set<ConstraintViolation<PortBean>> violations = validate("portInteger");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateNegativePortInteger() throws Exception {
        portBean.portInteger = -8080;

        Set<ConstraintViolation<PortBean>> violations = validate("portInteger");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void shouldNotValidateInvalidPortInteger() throws Exception {
        portBean.portInteger = 65537;

        Set<ConstraintViolation<PortBean>> violations = validate("portInteger");

        assertThat(violations, hasSize(1));
    }

    // others

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenAppliedToOtherTypes() throws Exception {
        portBean.portObject = new Object();

        validate("portObject");
    }

    private Set<ConstraintViolation<PortBean>> validate(String property) {
        return validator.validateProperty(portBean, property);
    }

    private static final class PortBean {
        @Port
        private String portString;
        @Port
        private int portInt;
        @Port
        private Integer portInteger;
        @Port
        private Object portObject;
    }
}
