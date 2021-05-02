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
package com.github.britter.beanvalidators.net.internal;

import com.github.britter.beanvalidators.internal.BlankStringAcceptingConstraintValidator;
import com.github.britter.beanvalidators.net.IP;
import com.github.britter.beanvalidators.net.IPType;
import org.apache.commons.validator.routines.InetAddressValidator;

import javax.validation.ConstraintValidatorContext;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public final class IPConstraintValidator implements BlankStringAcceptingConstraintValidator<IP> {

    private IPType type;

    @Override
    public void initialize(final IP constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValidNonBlankValue(String value, ConstraintValidatorContext context) {
        switch (type) {
            case IP_V4:
                return InetAddressValidator.getInstance().isValidInet4Address(value);
            case IP_V6:
                return InetAddressValidator.getInstance().isValidInet6Address(value);
            default:
                return InetAddressValidator.getInstance().isValid(value);
        }
    }

}
