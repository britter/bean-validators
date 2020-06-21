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

public final class NumericTest {

    private ValidationWrapper<NumericBean> validator;
    private NumericBean numericBean;

    @BeforeEach
    public void setUp() {
        numericBean = new NumericBean();
        validator = new ValidationWrapper<>(numericBean, "must be numeric");
    }

    @Test
    public void shouldValidateNumericString() {
        numericBean.numeric = "123456";

        validator.assertNoViolations("numeric");
    }

    @Test
    public void shouldValidateNullString() {
        numericBean.numeric = null;

        validator.assertNoViolations("numeric");
    }

    @Test
    public void shouldValidateBlankString() {
        numericBean.numeric = " ";

        validator.assertNoViolations("numeric");
    }

    @Test
    public void shouldNotValidateNonNumericString() {
        numericBean.numeric = "abc";

        validator.assertViolation("numeric");
    }

    private static final class NumericBean {
        @Numeric
        private String numeric;
    }
}
