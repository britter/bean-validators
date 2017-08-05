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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;
import java.util.Optional;

public class BeforeNowOffsetDateTimeConstraintValidator implements ConstraintValidator<BeforeNow, OffsetDateTime> {

    /**
     * Only for testing!
     */
    static Optional<OffsetDateTime> now = Optional.empty();

    @Override
    public void initialize(final BeforeNow constraintAnnotation) {
    }

    @Override
    public boolean isValid(final OffsetDateTime value, final ConstraintValidatorContext context) {
        // Don't validate null, since these are validated by @NotNull
        return value == null || (value.isBefore(now()));
    }

    private OffsetDateTime now() {
        return now.orElse(OffsetDateTime.now());
    }

}
