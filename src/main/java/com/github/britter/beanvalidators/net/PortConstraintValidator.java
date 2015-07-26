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
package com.github.britter.beanvalidators.net;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class PortConstraintValidator implements ConstraintValidator<Port, Object> {

    public static final int PORT_MIN_VALUE = 0;
    public static final int PORT_MAX_VALUE = 65536;

    @Override
    public void initialize(Port constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return isValidPort((String) value);
        } else if (value instanceof Integer) {
            return isValidPort((Integer) value);
        } else {
            throw new ValidationException("@Port can not be applied to objects of type " + value.getClass().getName());
        }
    }
    
    private boolean isValidPort(String port) {
        if (StringUtils.isBlank(port)) {
            return true;
        } else if (NumberUtils.isDigits(port)) {
            return isValidPort(Integer.parseInt(port));
        } else {
            return false;
        }
    }

    private boolean isValidPort(Integer port) {
        return port >= PORT_MIN_VALUE && port <= PORT_MAX_VALUE;
    }
}
