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

import javax.validation.ConstraintViolation;
import java.util.Set;

public final class AlphaNumericTest {

    private AlphaNumericBean alphaNumBean;
    private ValidationWrapper<AlphaNumericBean> validator;

    @BeforeEach
    public void setUp() {
        alphaNumBean = new AlphaNumericBean();
        validator = new ValidationWrapper<>(alphaNumBean, "must be alpha numeric");
    }

    @Test
    public void defaultSettingsShouldValidateAlphabeticString() {
        alphaNumBean.alphaNum = "abcd";

        validator.assertNoViolations("alphaNum");
    }

    @Test
    public void defaultSettingsShouldValidateAlphabeticNumericString() {
        alphaNumBean.alphaNum = "abcd1234";

        validator.assertNoViolations("alphaNum");
    }

    @Test
    public void defaultSettingsShouldValidateNull() {
        alphaNumBean.alphaNum = null;

        validator.assertNoViolations("alphaNum");
    }

    @Test
    public void defaultSettingsShouldValidateBlankString() {
        alphaNumBean.alphaNum = "";

        validator.assertNoViolations("alphaNum");
    }


    @Test
    public void defaultSettingsShouldNotValidateNonAlphabeticString() {
        alphaNumBean.alphaNum = "abcd?";

        Set<ConstraintViolation<AlphaNumericBean>> violations = validator.validate("alphaNum");

        validator.assertViolation("alphaNum");
    }

    @Test
    public void defaultSettingsShouldNotValidateAlphabeticStringWithSpaces() {
        alphaNumBean.alphaNum = "ab cd";

        validator.assertViolation("alphaNum");
    }

    @Test
    public void defaultSettingsShouldNotValidateAlphabeticNumericStringWithSpaces() {
        alphaNumBean.alphaNum = "ab cd 1234";

        validator.assertViolation("alphaNum");
    }


    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticString() {
        alphaNumBean.alphaNumSpace = "abcd";

        validator.assertNoViolations("alphaNumSpace");
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticNumericString() {
        alphaNumBean.alphaNumSpace = "abcd1234";

        validator.assertNoViolations("alphaNumSpace");
    }

    @Test
    public void allowSpacesSettingsShouldNotValidateNonAlphabeticString() {
        alphaNumBean.alphaNumSpace = "abcd123?";

        validator.assertViolation("alphaNumSpace");
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticStringWithSpaces() {
        alphaNumBean.alphaNumSpace = "ab cd";

        validator.assertNoViolations("alphaNumSpace");
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticNumericStringWithSpaces() {
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
