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

public class AlphabeticTest {

    private AlphabeticBean alphabeticBean;
    private ValidationWrapper<AlphabeticBean> validator;

    @Before
    public void setUp() {
        alphabeticBean = new AlphabeticBean();
        validator = new ValidationWrapper<>(alphabeticBean);
    }

    @Test
    public void defaultSettingsShouldValidateAlphabeticString() throws Exception {
        alphabeticBean.alphabetic = "abcd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validator.validate("alphabetic");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingsShouldNotValidateNonAlphabeticString() throws Exception {
        alphabeticBean.alphabetic = "abcd123";

        Set<ConstraintViolation<AlphabeticBean>> violations = validator.validate("alphabetic");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AlphabeticBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be alphabetic")));
    }

    @Test
    public void defaultSettingsShouldNotValidateAlphabeticStringWithSpaces() throws Exception {
        alphabeticBean.alphabetic = "ab cd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validator.validate("alphabetic");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticString() throws Exception {
        alphabeticBean.alphabeticSpace = "abcd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validator.validate("alphabeticSpace");

        assertThat(violations, is(empty()));
    }

    @Test
    public void allowSpacesSettingsShouldNotValidateNonAlphabeticString() throws Exception {
        alphabeticBean.alphabeticSpace = "abcd123";

        Set<ConstraintViolation<AlphabeticBean>> violations = validator.validate("alphabeticSpace");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AlphabeticBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be alphabetic")));
    }

    @Test
    public void allowSpacesSettingsShouldValidateAlphabeticStringWithSpaces() throws Exception {
        alphabeticBean.alphabeticSpace = "ab cd";

        Set<ConstraintViolation<AlphabeticBean>> violations = validator.validate("alphabeticSpace");

        assertThat(violations, is(empty()));
    }

    private static class AlphabeticBean {
        @Alphabetic
        private String alphabetic;

        @Alphabetic(allowSpaces = true)
        private String alphabeticSpace;
    }
}
