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

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

public final class JavaNumberTest {

    private JavaNumberBean numBean;
    private ValidationWrapper<JavaNumberBean> validator;

    @Before
    public void setUp() throws Exception {
        numBean = new JavaNumberBean();
        validator = new ValidationWrapper<>(numBean, "must be a valid Java number");
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        numBean.javaNum = null;

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        numBean.javaNum = " ";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateNumber() throws Exception {
        numBean.javaNum = "1234";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateNegativeNumber() throws Exception {
        numBean.javaNum = "-1234";

        validator.assertNoViolations("javaNum");
    }


    @Test
    public void shouldValidateNumberWithTypeQualifier() throws Exception {
        numBean.javaNum = "1234L";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateHexNumberLowerCaseX() throws Exception {
        numBean.javaNum = "0x1A";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateHexNumberUpperCaseX() throws Exception {
        numBean.javaNum = "0X1A";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateOctalNumber() throws Exception {
        numBean.javaNum = "017";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldValidateDecimalNumber() throws Exception {
        numBean.javaNum = "0.19";

        validator.assertNoViolations("javaNum");
    }

    @Test
    public void shouldNotValidateRandomString() throws Exception {
        numBean.javaNum = "abcd";

        validator.assertViolation("javaNum");
    }

    private static final class JavaNumberBean {
        @JavaNumber
        private String javaNum;
    }
}
