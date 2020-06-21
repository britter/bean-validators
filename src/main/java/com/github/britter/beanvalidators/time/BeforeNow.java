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
package com.github.britter.beanvalidators.time;

import com.github.britter.beanvalidators.time.internal.BeforeNowInstantConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowLocalDateConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowLocalDateTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowLocalTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowOffsetDateTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowOffsetTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowYearConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowYearMonthConstraintValidator;
import com.github.britter.beanvalidators.time.internal.BeforeNowZonedDateTimeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Makes sure the validated temporal unit is before now.
 *
 * <p>
 * Can be applied to {@link java.time.Instant}, {@link java.time.LocalDate}, {@link java.time.LocalDateTime},
 * {@link java.time.LocalTime}, {@link java.time.OffsetDateTime}, {@link java.time.OffsetTime},
 * {@link java.time.ZonedDateTime}, {@link java.time.YearMonth}, and {@link java.time.Year}.
 * </p>
 *
 * @since 1.0.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {
        BeforeNowLocalDateConstraintValidator.class,
        BeforeNowLocalDateTimeConstraintValidator.class,
        BeforeNowLocalTimeConstraintValidator.class,
        BeforeNowInstantConstraintValidator.class,
        BeforeNowOffsetDateTimeConstraintValidator.class,
        BeforeNowOffsetTimeConstraintValidator.class,
        BeforeNowZonedDateTimeConstraintValidator.class,
        BeforeNowYearMonthConstraintValidator.class,
        BeforeNowYearConstraintValidator.class,
})
@Documented
public @interface BeforeNow {

    String message() default "{com.github.britter.beanvalidators.time.BeforeNow.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
