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

public final class ASCIITest {

    private ASCIIBean asciiBean;
    private ValidationWrapper<ASCIIBean> validator;

    @Before
    public void setUp() {
        asciiBean = new ASCIIBean();
        validator = new ValidationWrapper<>(asciiBean, "must be ASCII printable");
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        asciiBean.ascii = null;

        validator.assertNoViolations("ascii");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        asciiBean.ascii = " ";

        validator.assertNoViolations("ascii");
    }

    @Test
    public void shouldValidateAscii() throws Exception {
        asciiBean.ascii = "abcd";

        validator.assertNoViolations("ascii");
    }

    @Test
    public void shouldNotValidateNonAscii() throws Exception {
        asciiBean.ascii = "äöü";

        validator.assertViolation("ascii");
    }

    private static final class ASCIIBean {
        @ASCII
        private String ascii;
    }
}
