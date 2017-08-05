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

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Optional;

public class BeforeNowTest {

    private BeforeNowBean beforeNowBean;
    private ValidationWrapper<BeforeNowBean> validator;

    @Before
    public void setUp() {
        beforeNowBean = new BeforeNowBean();
        validator = new ValidationWrapper<>(beforeNowBean, "must be before now");
    }

    @Test
    public void shouldValidateLocalDateBeforeNow() throws Exception {
        beforeNowBean.localDate = LocalDate.now().minusDays(1);

        validator.assertNoViolations("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateNow() throws Exception {
        beforeNowBean.localDate = LocalDate.now();

        validator.assertViolation("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateAfterNow() throws Exception {
        beforeNowBean.localDate = LocalDate.now().plusDays(1);

        validator.assertViolation("localDate");
    }

    @Test
    public void shouldValidateLocalDateTimeBeforeNow() throws Exception {
        beforeNowBean.localDateTime = LocalDateTime.now().minusDays(1);

        validator.assertNoViolations("localDateTime");
    }

    @Test
    public void shouldNotValidateLocalDateTimeNow() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        BeforeNowLocalDateTimeConstraintValidator.now = Optional.of(now);

        beforeNowBean.localDateTime = now;

        validator.assertViolation("localDateTime");
    }

    @Test
    public void shouldNotValidateLocalDateTimeAfterNow() throws Exception {
        beforeNowBean.localDateTime = LocalDateTime.now().plusDays(1);

        validator.assertViolation("localDateTime");
    }

    @Test
    public void shouldValidateLocalTimeBeforeNow() throws Exception {
        beforeNowBean.localTime = LocalTime.now().minusHours(1);

        validator.assertNoViolations("localTime");
    }

    @Test
    public void shouldNotValidateLocalTimeNow() throws Exception {
        LocalTime now = LocalTime.now();
        BeforeNowLocalTimeConstraintValidator.now = Optional.of(now);

        beforeNowBean.localTime = now;

        validator.assertViolation("localTime");
    }

    @Test
    public void shouldNotValidateLocalTimeAfterNow() throws Exception {
        beforeNowBean.localTime = LocalTime.now().plusHours(1);

        validator.assertViolation("localTime");
    }

    @Test
    public void shouldValidateInstantBeforeNow() throws Exception {
        beforeNowBean.instant = Instant.now().minusMillis(1000);

        validator.assertNoViolations("instant");
    }

    @Test
    public void shouldNotValidateInstantNow() throws Exception {
        Instant now = Instant.now();
        BeforeNowInstantConstraintValidator.now = Optional.of(now);

        beforeNowBean.instant = now;

        validator.assertViolation("instant");
    }

    @Test
    public void shouldNotValidateInstantAfterNow() throws Exception {
        beforeNowBean.instant = Instant.now().plusMillis(1000);

        validator.assertViolation("instant");
    }

    @Test
    public void shouldValidateOffsetDateTimeBeforeNow() throws Exception {
        beforeNowBean.offsetDateTime = OffsetDateTime.now().minusDays(1);

        validator.assertNoViolations("offsetDateTime");
    }

    @Test
    public void shouldNotValidateOffsetDateTimeNow() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        BeforeNowOffsetDateTimeConstraintValidator.now = Optional.of(now);

        beforeNowBean.offsetDateTime = now;

        validator.assertViolation("offsetDateTime");
    }

    @Test
    public void shouldNotValidateOffsetDateTimeAfterNow() throws Exception {
        beforeNowBean.offsetDateTime = OffsetDateTime.now().plusDays(1);

        validator.assertViolation("offsetDateTime");
    }

    private static class BeforeNowBean {
        @BeforeNow
        private LocalDate localDate;
        @BeforeNow
        private LocalDateTime localDateTime;
        @BeforeNow
        private LocalTime localTime;
        @BeforeNow
        private Instant instant;
        @BeforeNow
        private OffsetDateTime offsetDateTime;
        @BeforeNow
        private OffsetTime offsetTime;
        @BeforeNow
        private ZonedDateTime zonedDateTime;
    }
}
