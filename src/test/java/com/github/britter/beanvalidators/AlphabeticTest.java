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

public class AlphabeticTest {

    private AlphabeticBean alphabeticBean;
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        alphabeticBean = new AlphabeticBean();
    }

    @Test
    public void defaultSettingsShouldValidateAlphabeticString() throws Exception {
        alphabeticBean.alphabetic = "abcd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validate("alphabetic");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingsShouldNotValidateNonAlphabeticString() throws Exception {
        alphabeticBean.alphabetic = "abcd123";

        Set<ConstraintViolation<AlphabeticBean>> violations = validate("alphabetic");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AlphabeticBean> violation = getFirst(violations);
        assertThat(violation.getMessage(), is(equalTo("must be alphabetic")));
    }

    @Test
    public void defaultSettingsShouldNotValidateAlphabeticStringWithSpaces() throws Exception {
        alphabeticBean.alphabetic = "ab cd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validate("alphabetic");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticString() throws Exception {
        alphabeticBean.alphabeticSpace = "abcd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validate("alphabeticSpace");

        assertThat(violations, is(empty()));
    }

    @Test
    public void allowSpacesSettingsShouldNotValidateNonAlphabeticString() throws Exception {
        alphabeticBean.alphabeticSpace = "abcd123";

        Set<ConstraintViolation<AlphabeticBean>> violations = validate("alphabeticSpace");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AlphabeticBean> violation = getFirst(violations);
        assertThat(violation.getMessage(), is(equalTo("must be alphabetic")));
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticStringWithSpaces() throws Exception {
        alphabeticBean.alphabeticSpace = "ab cd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validate("alphabeticSpace");

        assertThat(violations, is(empty()));
    }

    private Set<ConstraintViolation<AlphabeticBean>> validate(String property) {
        return validator.validateProperty(alphabeticBean, property);
    }

    private static <T> T getFirst(Set<T> set) {
        return set.iterator().next();
    }

    private static class AlphabeticBean {
        @Alphabetic
        private String alphabetic;

        @Alphabetic(allowSpaces = true)
        private String alphabeticSpace;
    }
}
