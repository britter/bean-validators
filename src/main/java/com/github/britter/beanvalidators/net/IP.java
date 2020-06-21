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
package com.github.britter.beanvalidators.net;

import com.github.britter.beanvalidators.net.internal.IPConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Makes sure a String represents a valid <a href="https://en.wikipedia.org/wiki/IP_address">Internet Protocol (IP) address</a>.
 *
 * <p>There are two kinds of IP addresses: IPv4 and the newer IPv6. The default is to validate against both versions.
 * This behavior can be controlled by the {@link IPType} enum.</p>
 *
 * @since 0.3
 */
@Target({METHOD, FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = IPConstraintValidator.class)
@Documented
public @interface IP {

    IPType type() default IPType.ALL;

    String message() default "{com.github.britter.beanvalidators.net.IP.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
