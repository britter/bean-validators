/*
 * Copyright 2015 Benedikt Ritter
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
package com.github.britter.beanvalidators.strings.internal;

import com.github.britter.beanvalidators.internal.BlankStringAcceptingConstraintValidator;
import com.github.britter.beanvalidators.strings.ISBN;
import com.github.britter.beanvalidators.strings.ISBNType;
import org.apache.commons.validator.routines.ISBNValidator;

import javax.validation.ConstraintValidatorContext;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class ISBNConstraintValidator implements BlankStringAcceptingConstraintValidator<ISBN> {

    private ISBNType type;

    @Override
    public void initialize(final ISBN constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValidNonBlankValue(String value, ConstraintValidatorContext context) {
        switch (type) {
            case ISBN_10:
                return ISBNValidator.getInstance().isValidISBN10(value);
            case ISBN_13:
                return ISBNValidator.getInstance().isValidISBN13(value);
            default:
                return ISBNValidator.getInstance().isValid(value);
        }
    }

}
