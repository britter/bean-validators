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

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PresentTest {

    private PresentBean presentBean;
    private ValidationWrapper<PresentBean> validator;

    @Before
    public void setUp() throws Exception {
        presentBean = new PresentBean();
        validator = new ValidationWrapper<>(presentBean, "must be present");
    }

    @Test
    public void shouldValidateNullOptional() throws Exception {
        presentBean.optional = null;

        validator.assertNoViolations("optional");
    }

    @Test
    public void shouldNotValidateEmptyOptional() throws Exception {
        presentBean.optional = Optional.empty();

        validator.assertViolation("optional");
    }

    @Test
    public void shouldValidatePresentOptional() throws Exception {
        presentBean.optional = Optional.of("some");

        validator.assertNoViolations("optional");
    }

    private static class PresentBean {
        @Present
        private Optional<String> optional;
    }
}
