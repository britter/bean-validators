/*
 * Copyright 2017 Benedikt Ritter
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
package com.github.britter.beanvalidators.time;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AfterNowTest {

    private AfterNowBean afterNowBean;
    private ValidationWrapper<AfterNowBean> validator;

    @Before
    public void setUp() {
        afterNowBean = new AfterNowBean();
        validator = new ValidationWrapper<>(afterNowBean, "must be after now");
    }

    @Test
    public void shouldValidateLocalDateAfterNow() throws Exception {
        afterNowBean.localDate = LocalDate.now().plusDays(1);

        validator.assertNoViolations("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateNow() throws Exception {
        afterNowBean.localDate = LocalDate.now();

        validator.assertViolation("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateBeforeNow() throws Exception {
        afterNowBean.localDate = LocalDate.now().minusDays(1);

        validator.assertViolation("localDate");
    }

    private static class AfterNowBean {
        @AfterNow
        private LocalDate localDate;
    }
}
