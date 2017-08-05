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

public class BlankTest {

    private BlankBean blankBean;
    private ValidationWrapper<BlankBean> validator;

    @Before
    public void setUp() {
        blankBean = new BlankBean();
        validator = new ValidationWrapper<>(blankBean, "must be blank");
    }

    @Test
    public void shouldValidateEmptyString() throws Exception {
        blankBean.blank = "";

        validator.assertNoViolations("blank");
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        blankBean.blank = null;

        validator.assertNoViolations("blank");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        blankBean.blank = "  ";

        validator.assertNoViolations("blank");
    }

    @Test
    public void shouldNotValidateNonBlankString() throws Exception {
        blankBean.blank = " abcd ";

        validator.assertViolation("blank");
    }

    private static final class BlankBean {
        @Blank
        private String blank;
    }
}
