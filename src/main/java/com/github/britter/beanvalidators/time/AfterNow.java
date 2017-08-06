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

import com.github.britter.beanvalidators.time.internal.AfterNowInstantConstraintValidator;
import com.github.britter.beanvalidators.time.internal.AfterNowLocalDateConstraintValidator;
import com.github.britter.beanvalidators.time.internal.AfterNowLocalDateTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.AfterNowLocalTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.AfterNowOffsetDateTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.AfterNowOffsetTimeConstraintValidator;
import com.github.britter.beanvalidators.time.internal.AfterNowZonedDateTimeConstraintValidator;

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
 * Makes sure the validated temporal unit is after now.
 *
 * <p>
 * Can be applied to {@link java.time.Instant}, {@link java.time.LocalDate}, {@link java.time.LocalDateTime},
 * {@link java.time.LocalTime}, {@link java.time.OffsetDateTime}, {@link java.time.OffsetTime},
 * and {@link java.time.ZonedDateTime}.
 * </p>
 *
 * @since 1.0.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {
        AfterNowLocalDateConstraintValidator.class,
        AfterNowLocalDateTimeConstraintValidator.class,
        AfterNowLocalTimeConstraintValidator.class,
        AfterNowInstantConstraintValidator.class,
        AfterNowOffsetDateTimeConstraintValidator.class,
        AfterNowOffsetTimeConstraintValidator.class,
        AfterNowZonedDateTimeConstraintValidator.class,
})
@Documented
public @interface AfterNow {

    String message() default "{com.github.britter.beanvalidators.time.AfterNow.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
