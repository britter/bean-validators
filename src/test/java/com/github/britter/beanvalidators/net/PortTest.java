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

import javax.validation.ValidationException;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

public class PortTest {

    private ValidationWrapper<PortBean> validator;
    private PortBean portBean;

    @Before
    public void setUp() throws Exception {
        portBean = new PortBean();
        validator = new ValidationWrapper<>(portBean, "must be a port number");
    }

    // String

    @Test
    public void shouldValidateNullString() throws Exception {
        portBean.portString = null;

        validator.assertNoViolations("portString");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        portBean.portString = " ";

        validator.assertNoViolations("portString");
    }

    @Test
    public void shouldNotValidateRandomString() throws Exception {
        portBean.portString = "abcd";

        validator.assertViolation("portString");
    }

    @Test
    public void shouldValidateValidPortString() throws Exception {
        portBean.portString = "8080";

        validator.assertNoViolations("portString");
    }

    @Test
    public void shouldNotValidateNegativePortString() throws Exception {
        portBean.portString = "-8080";

        validator.assertViolation("portString");
    }

    @Test
    public void shouldNotValidateInvalidPortString() throws Exception {
        portBean.portString = "65537";

        validator.assertViolation("portString");
    }

    // int

    @Test
    public void shouldValidateValidPortInt() throws Exception {
        portBean.portInt = 8080;

        validator.assertNoViolations("portInt");
    }

    @Test
    public void shouldNotValidateNegativePortInt() throws Exception {
        portBean.portInt = -8080;

        validator.assertViolation("portInt");
    }

    @Test
    public void shouldNotValidateInvalidPortInt() throws Exception {
        portBean.portInt = 65537;

        validator.assertViolation("portInt");
    }

    // Integer
    @Test
    public void shouldValidateNullPortInteger() throws Exception {
        portBean.portInteger = null;

        validator.assertNoViolations("portInteger");
    }

    @Test
    public void shouldNotValidateNegativePortInteger() throws Exception {
        portBean.portInteger = -8080;

        validator.assertViolation("portInteger");
    }

    @Test
    public void shouldNotValidateInvalidPortInteger() throws Exception {
        portBean.portInteger = 65537;

        validator.assertViolation("portInteger");
    }

    // others

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenAppliedToOtherTypes() throws Exception {
        portBean.portObject = new Object();

        validator.validate("portObject");
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
