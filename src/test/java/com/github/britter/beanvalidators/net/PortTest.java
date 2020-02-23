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

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class PortTest {

    private ValidationWrapper<PortBean> validator;
    private PortBean portBean;

    @BeforeEach
    public void setUp() {
        portBean = new PortBean();
        validator = new ValidationWrapper<>(portBean, "must be a port number");
    }

    @Nested
    class WithStrings {

        @Test
        public void shouldValidateNullString() {
            portBean.portString = null;

            validator.assertNoViolations("portString");
        }

        @Test
        public void shouldValidateBlankString() {
            portBean.portString = " ";

            validator.assertNoViolations("portString");
        }

        @Test
        public void shouldNotValidateRandomString() {
            portBean.portString = "abcd";

            validator.assertViolation("portString");
        }

        @Test
        public void shouldValidateValidPortString() {
            portBean.portString = "8080";

            validator.assertNoViolations("portString");
        }

        @Test
        public void shouldNotValidateNegativePortString() {
            portBean.portString = "-8080";

            validator.assertViolation("portString");
        }

        @Test
        public void shouldNotValidateInvalidPortString() {
            portBean.portString = "65537";

            validator.assertViolation("portString");
        }
    }

    @Nested
    class WithInts {

        @Test
        public void shouldValidateValidPortInt() {
            portBean.portInt = 8080;

            validator.assertNoViolations("portInt");
        }

        @Test
        public void shouldNotValidateNegativePortInt() {
            portBean.portInt = -8080;

            validator.assertViolation("portInt");
        }

        @Test
        public void shouldNotValidateInvalidPortInt() {
            portBean.portInt = 65537;

            validator.assertViolation("portInt");
        }
    }

    @Nested
    class WithIntegers {

        @Test
        public void shouldValidateNullPortInteger() {
            portBean.portInteger = null;

            validator.assertNoViolations("portInteger");
        }

        @Test
        public void shouldNotValidateNegativePortInteger() {
            portBean.portInteger = -8080;

            validator.assertViolation("portInteger");
        }

        @Test
        public void shouldNotValidateInvalidPortInteger() {
            portBean.portInteger = 65537;

            validator.assertViolation("portInteger");
        }
    }

    @Test
    public void shouldThrowExceptionWhenAppliedToOtherTypes() {
        portBean.portObject = new Object();

        assertThrows(ValidationException.class, () -> validator.validate("portObject"));
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
