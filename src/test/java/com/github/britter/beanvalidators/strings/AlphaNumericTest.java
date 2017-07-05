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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolation;
import java.util.Set;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

public class AlphaNumericTest {

    private AlphaNumericBean alphaNumBean;
    private ValidationWrapper<AlphaNumericBean> validator;

    @Before
    public void setUp() {
        alphaNumBean = new AlphaNumericBean();
        validator = new ValidationWrapper<>(alphaNumBean, null);
    }

    @Test
    public void defaultSettingsShouldValidateAlphabeticString() throws Exception {
        alphaNumBean.alphaNum = "abcd";

        validator.assertNoViolations("alphaNum");
    }

    @Test
    public void defaultSettingsShouldValidateAlphabeticNumericString() throws Exception {
        alphaNumBean.alphaNum = "abcd1234";

        validator.assertNoViolations("alphaNum");
    }

    @Test
    public void defaultSettingsShouldValidateNull() throws Exception {
        alphaNumBean.alphaNum = null;

        validator.assertNoViolations("alphaNum");
    }

    @Test
    public void defaultSettingsShouldValidateBlankString() throws Exception {
        alphaNumBean.alphaNum = "";

        validator.assertNoViolations("alphaNum");
    }


    @Test
    public void defaultSettingsShouldNotValidateNonAlphabeticString() throws Exception {
        alphaNumBean.alphaNum = "abcd?";

        Set<ConstraintViolation<AlphaNumericBean>> violations = validator.validate("alphaNum");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AlphaNumericBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be alpha numeric")));
    }

    @Test
    public void defaultSettingsShouldNotValidateAlphabeticStringWithSpaces() throws Exception {
        alphaNumBean.alphaNum = "ab cd";

        Set<ConstraintViolation<AlphaNumericBean>> violations = validator.validate("alphaNum");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void defaultSettingsShouldNotValidateAlphabeticNumericStringWithSpaces() throws Exception {
        alphaNumBean.alphaNum = "ab cd 1234";

        Set<ConstraintViolation<AlphaNumericBean>> violations = validator.validate("alphaNum");

        assertThat(violations, hasSize(1));
    }


    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticString() throws Exception {
        alphaNumBean.alphaNumSpace = "abcd";

        validator.assertNoViolations("alphaNumSpace");
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticNumericString() throws Exception {
        alphaNumBean.alphaNumSpace = "abcd1234";

        validator.assertNoViolations("alphaNumSpace");
    }

    @Test
    public void allowSpacesSettingsShouldNotValidateNonAlphabeticString() throws Exception {
        alphaNumBean.alphaNumSpace = "abcd123?";

        Set<ConstraintViolation<AlphaNumericBean>> violations = validator.validate("alphaNumSpace");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AlphaNumericBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be alpha numeric")));
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticStringWithSpaces() throws Exception {
        alphaNumBean.alphaNumSpace = "ab cd";

        validator.assertNoViolations("alphaNumSpace");
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticNumericStringWithSpaces() throws Exception {
        alphaNumBean.alphaNumSpace = "ab cd 1234";

        validator.assertNoViolations("alphaNumSpace");
    }

    private static class AlphaNumericBean {
        @AlphaNumeric
        private String alphaNum;

        @AlphaNumeric(allowSpaces = true)
        private String alphaNumSpace;
    }
}
