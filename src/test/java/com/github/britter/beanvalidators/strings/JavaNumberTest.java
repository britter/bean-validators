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
import java.util.Set;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

public class JavaNumberTest {

    private JavaNumberBean numBean;
    private ValidationWrapper<JavaNumberBean> validator;

    @Before
    public void setUp() throws Exception {
        numBean = new JavaNumberBean();
        validator = new ValidationWrapper<>(numBean);
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        numBean.javaNum = null;

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        numBean.javaNum = " ";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateNumber() throws Exception {
        numBean.javaNum = "1234";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateNegativeNumber() throws Exception {
        numBean.javaNum = "-1234";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }


    @Test
    public void shouldValidateNumberWithTypeQualifier() throws Exception {
        numBean.javaNum = "1234L";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateHexNumberLowerCaseX() throws Exception {
        numBean.javaNum = "0x1A";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateHexNumberUpperCaseX() throws Exception {
        numBean.javaNum = "0X1A";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateOctalNumber() throws Exception {
        numBean.javaNum = "017";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateDecimalNumber() throws Exception {
        numBean.javaNum = "0.19";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateRandomString() throws Exception {
        numBean.javaNum = "abcd";

        Set<ConstraintViolation<JavaNumberBean>> violations = validator.validate("javaNum");

        assertThat(violations, hasSize(1));
        ConstraintViolation<JavaNumberBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be a valid Java number")));
    }

    private static final class JavaNumberBean {
        @JavaNumber
        private String javaNum;
    }
}
