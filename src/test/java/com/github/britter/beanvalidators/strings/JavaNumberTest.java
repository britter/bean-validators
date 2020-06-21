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

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class JavaNumberTest {

    private JavaNumberBean numBean;
    private ValidationWrapper<JavaNumberBean> validator;

    @BeforeEach
    public void setUp() {
        numBean = new JavaNumberBean();
        validator = new ValidationWrapper<>(numBean, "must be a valid Java number");
    }

    @Test
    public void shouldValidateNullString() {
        numBean.javaNum = null;

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateBlankString() {
        numBean.javaNum = " ";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateNumber() {
        numBean.javaNum = "1234";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateNegativeNumber() {
        numBean.javaNum = "-1234";

        validator.assertNoViolations("javaNum");
    }


    @Test
    public void shouldValidateNumberWithTypeQualifier() {
        numBean.javaNum = "1234L";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateHexNumberLowerCaseX() {
        numBean.javaNum = "0x1A";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateHexNumberUpperCaseX() {
        numBean.javaNum = "0X1A";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateOctalNumber() {
        numBean.javaNum = "017";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateDecimalNumber() {
        numBean.javaNum = "0.19";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldNotValidateRandomString() {
        numBean.javaNum = "abcd";

        validator.assertViolation("javaNum");
    }

    private static final class JavaNumberBean {
        @JavaNumber
        private String javaNum;
    }
}
