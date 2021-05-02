/*
 * Copyright 2017 Benedikt Ritter
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
package com.github.britter.beanvalidators.time.internal;

import com.github.britter.beanvalidators.ValidationWrapper;
import com.github.britter.beanvalidators.time.BeforeNow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.Optional;

public final class BeforeNowTest {

    private BeforeNowBean beforeNowBean;
    private ValidationWrapper<BeforeNowBean> validator;

    @BeforeEach
    public void setUp() {
        beforeNowBean = new BeforeNowBean();
        validator = new ValidationWrapper<>(beforeNowBean, "must be before now");
    }

    @Test
    public void shouldValidateLocalDateBeforeNow() {
        beforeNowBean.localDate = LocalDate.now().minusDays(1);

        validator.assertNoViolations("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateNow() {
        beforeNowBean.localDate = LocalDate.now();

        validator.assertViolation("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateAfterNow() {
        beforeNowBean.localDate = LocalDate.now().plusDays(1);

        validator.assertViolation("localDate");
    }

    @Test
    public void shouldValidateLocalDateTimeBeforeNow() {
        beforeNowBean.localDateTime = LocalDateTime.now().minusDays(1);

        validator.assertNoViolations("localDateTime");
    }

    @Test
    public void shouldNotValidateLocalDateTimeNow() {
        LocalDateTime now = LocalDateTime.now();
        BeforeNowLocalDateTimeConstraintValidator.suppliedNow = Optional.of(now);

        beforeNowBean.localDateTime = now;

        validator.assertViolation("localDateTime");
    }

    @Test
    public void shouldNotValidateLocalDateTimeAfterNow() {
        beforeNowBean.localDateTime = LocalDateTime.now().plusDays(1);

        validator.assertViolation("localDateTime");
    }

    @Test
    public void shouldValidateLocalTimeBeforeNow() {
        beforeNowBean.localTime = LocalTime.now().minusHours(1);

        validator.assertNoViolations("localTime");
    }

    @Test
    public void shouldNotValidateLocalTimeNow() {
        LocalTime now = LocalTime.now();
        BeforeNowLocalTimeConstraintValidator.suppliedNow = Optional.of(now);

        beforeNowBean.localTime = now;

        validator.assertViolation("localTime");
    }

    @Test
    public void shouldNotValidateLocalTimeAfterNow() {
        beforeNowBean.localTime = LocalTime.now().plusHours(1);

        validator.assertViolation("localTime");
    }

    @Test
    public void shouldValidateInstantBeforeNow() {
        beforeNowBean.instant = Instant.now().minusMillis(1000);

        validator.assertNoViolations("instant");
    }

    @Test
    public void shouldNotValidateInstantNow() {
        Instant now = Instant.now();
        BeforeNowInstantConstraintValidator.suppliedNow = Optional.of(now);

        beforeNowBean.instant = now;

        validator.assertViolation("instant");
    }

    @Test
    public void shouldNotValidateInstantAfterNow() {
        beforeNowBean.instant = Instant.now().plusMillis(1000);

        validator.assertViolation("instant");
    }

    @Test
    public void shouldValidateOffsetDateTimeBeforeNow() {
        beforeNowBean.offsetDateTime = OffsetDateTime.now().minusDays(1);

        validator.assertNoViolations("offsetDateTime");
    }

    @Test
    public void shouldNotValidateOffsetDateTimeNow() {
        OffsetDateTime now = OffsetDateTime.now();
        BeforeNowOffsetDateTimeConstraintValidator.suppliedNow = Optional.of(now);

        beforeNowBean.offsetDateTime = now;

        validator.assertViolation("offsetDateTime");
    }

    @Test
    public void shouldNotValidateOffsetDateTimeAfterNow() {
        beforeNowBean.offsetDateTime = OffsetDateTime.now().plusDays(1);

        validator.assertViolation("offsetDateTime");
    }

    @Test
    public void shouldValidateOffsetTimeBeforeNow() {
        beforeNowBean.offsetTime = OffsetTime.now().minusHours(1);

        validator.assertNoViolations("offsetTime");
    }

    @Test
    public void shouldNotValidateOffsetTimeNow() {
        OffsetTime now = OffsetTime.now();
        BeforeNowOffsetTimeConstraintValidator.suppliedNow = Optional.of(now);

        beforeNowBean.offsetTime = now;

        validator.assertViolation("offsetTime");
    }

    @Test
    public void shouldNotValidateOffsetTimeAfterNow() {
        beforeNowBean.offsetTime = OffsetTime.now().plusHours(1);

        validator.assertViolation("offsetTime");
    }

    @Test
    public void shouldValidateZonedDateTimeBeforeNow() {
        beforeNowBean.zonedDateTime = ZonedDateTime.now().minusHours(1);

        validator.assertNoViolations("zonedDateTime");
    }

    @Test
    public void shouldNotValidateZonedDateTimeNow() {
        ZonedDateTime now = ZonedDateTime.now();
        BeforeNowZonedDateTimeConstraintValidator.suppliedNow = Optional.of(now);

        beforeNowBean.zonedDateTime = now;

        validator.assertViolation("zonedDateTime");
    }

    @Test
    public void shouldNotValidateZonedDateTimeAfterNow() {
        beforeNowBean.zonedDateTime = ZonedDateTime.now().plusHours(1);

        validator.assertViolation("zonedDateTime");
    }
    
    @Test
    public void shouldValidateYearMonthBeforeNow() {
        beforeNowBean.yearMonth = YearMonth.now().minusMonths(1);

        validator.assertNoViolations("yearMonth");
    }

    @Test
    public void shouldNotValidateYearMonthNow() {
        beforeNowBean.yearMonth = YearMonth.now();

        validator.assertViolation("yearMonth");
    }

    @Test
    public void shouldNotValidateYearMonthAfterNow() {
        beforeNowBean.yearMonth = YearMonth.now().plusMonths(1);

        validator.assertViolation("yearMonth");
    }    

    @Test
    public void shouldValidateYearBeforeNow() {
        beforeNowBean.year = Year.now().minusYears(1);

        validator.assertNoViolations("year");
    }

    @Test
    public void shouldNotValidateYearNow() {
        beforeNowBean.year = Year.now();

        validator.assertViolation("year");
    }

    @Test
    public void shouldNotValidateYearAfterNow() {
        beforeNowBean.year = Year.now().plusYears(1);

        validator.assertViolation("year");
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
        @BeforeNow
        private YearMonth yearMonth;
        @BeforeNow
        private Year year;
    }
}
