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

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

public final class DomainTest {

    private ValidationWrapper<DomainBean> validator;
    private DomainBean domainBean;

    @Before
    public void setUp() throws Exception {
        domainBean = new DomainBean();
        validator = new ValidationWrapper<>(domainBean, "must be a domain");
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        domainBean.domain = null;

        validator.assertNoViolations("domain");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        domainBean.domain = " ";

        validator.assertNoViolations("domain");
    }

    @Test
    public void shouldValidateDomain() throws Exception {
        domainBean.domain = "www.example.com";

        validator.assertNoViolations("domain");
    }

    @Test
    public void shouldNotValidateRandomString() throws Exception {
        domainBean.domain = "abcd";

        validator.assertViolation("domain");
    }

    private static final class DomainBean {
        @Domain
        private String domain;
    }
}
