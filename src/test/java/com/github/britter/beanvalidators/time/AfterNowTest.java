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
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

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

    @Test
    public void shouldValidateLocalDateTimeAfterNow() throws Exception {
        afterNowBean.localDateTime = LocalDateTime.now().plusDays(1);

        validator.assertNoViolations("localDateTime");
    }

    @Test
    public void shouldNotValidateLocalDateTimeNow() throws Exception {
        afterNowBean.localDateTime = LocalDateTime.now();

        validator.assertViolation("localDateTime");
    }

    @Test
    public void shouldNotValidateLocalDateTimeBeforeNow() throws Exception {
        afterNowBean.localDateTime = LocalDateTime.now().minusDays(1);

        validator.assertViolation("localDateTime");
    }

    @Test
    public void shouldValidateLocalTimeAfterNow() throws Exception {
        afterNowBean.localTime = LocalTime.now().plusHours(1);

        validator.assertNoViolations("localTime");
    }

    @Test
    public void shouldNotValidateLocalTimeNow() throws Exception {
        afterNowBean.localTime = LocalTime.now();

        validator.assertViolation("localTime");
    }

    @Test
    public void shouldNotValidateLocalTimeBeforeNow() throws Exception {
        afterNowBean.localTime = LocalTime.now().minusHours(1);

        validator.assertViolation("localTime");
    }

    @Test
    public void shouldValidateInstantAfterNow() throws Exception {
        afterNowBean.instant = Instant.now().plusMillis(1000);

        validator.assertNoViolations("instant");
    }

    @Test
    public void shouldNotValidateInstantNow() throws Exception {
        afterNowBean.instant = Instant.now();

        validator.assertViolation("instant");
    }

    @Test
    public void shouldNotValidateInstantBeforeNow() throws Exception {
        afterNowBean.instant = Instant.now().minusMillis(1000);

        validator.assertViolation("instant");
    }

    @Test
    public void shouldValidateOffsetDateTimeAfterNow() throws Exception {
        afterNowBean.offsetDateTime = OffsetDateTime.now().plusDays(1);

        validator.assertNoViolations("offsetDateTime");
    }

    @Test
    public void shouldNotValidateOffsetDateTimeNow() throws Exception {
        afterNowBean.offsetDateTime = OffsetDateTime.now();

        validator.assertViolation("offsetDateTime");
    }

    @Test
    public void shouldNotValidateOffsetDateTimeBeforeNow() throws Exception {
        afterNowBean.offsetDateTime = OffsetDateTime.now().minusDays(1);

        validator.assertViolation("offsetDateTime");
    }

    @Test
    public void shouldValidateOffsetTimeAfterNow() throws Exception {
        afterNowBean.offsetTime = OffsetTime.now().plusHours(1);

        validator.assertNoViolations("offsetTime");
    }

    @Test
    public void shouldNotValidateOffsetTimeNow() throws Exception {
        afterNowBean.offsetTime = OffsetTime.now();

        validator.assertViolation("offsetTime");
    }

    @Test
    public void shouldNotValidateOffsetTimeBeforeNow() throws Exception {
        afterNowBean.offsetTime = OffsetTime.now().minusHours(1);

        validator.assertViolation("offsetTime");
    }

    @Test
    public void shouldValidateZonedDateTimeAfterNow() throws Exception {
        afterNowBean.zonedDateTime = ZonedDateTime.now().plusHours(1);

        validator.assertNoViolations("zonedDateTime");
    }

    @Test
    public void shouldNotValidateZonedDateTimeNow() throws Exception {
        afterNowBean.zonedDateTime = ZonedDateTime.now();

        validator.assertViolation("zonedDateTime");
    }

    @Test
    public void shouldNotValidateZonedDateTimeBeforeNow() throws Exception {
        afterNowBean.zonedDateTime = ZonedDateTime.now().minusHours(1);

        validator.assertViolation("zonedDateTime");
    }

    private static class AfterNowBean {
        @AfterNow
        private LocalDate localDate;
        @AfterNow
        private LocalDateTime localDateTime;
        @AfterNow
        private LocalTime localTime;
        @AfterNow
        private Instant instant;
        @AfterNow
        private OffsetDateTime offsetDateTime;
        @AfterNow
        private OffsetTime offsetTime;
        @AfterNow
        private ZonedDateTime zonedDateTime;
    }
}
