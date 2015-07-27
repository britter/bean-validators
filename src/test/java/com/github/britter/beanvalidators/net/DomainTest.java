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

import static com.google.common.collect.Iterables.getLast;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolation;
import java.util.Set;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

public class DomainTest {

    private ValidationWrapper<DomainBean> validator;
    private DomainBean domainBean;

    @Before
    public void setUp() throws Exception {
        domainBean = new DomainBean();
        validator = new ValidationWrapper<>(domainBean);
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        domainBean.domain = null;

        Set<ConstraintViolation<DomainBean>> violations = validator.validate("domain");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        domainBean.domain = " ";

        Set<ConstraintViolation<DomainBean>> violations = validator.validate("domain");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateDomain() throws Exception {
        domainBean.domain = "www.example.com";

        Set<ConstraintViolation<DomainBean>> violations = validator.validate("domain");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateRandomString() throws Exception {
        domainBean.domain = "abcd";

        Set<ConstraintViolation<DomainBean>> violations = validator.validate("domain");

        assertThat(violations, hasSize(1));
        ConstraintViolation<DomainBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be a domain")));
    }

    private static final class DomainBean {
        @Domain
        private String domain;
    }
}
