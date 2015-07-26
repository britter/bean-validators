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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class IPTest {

    private IPBean ipBean;
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        ipBean = new IPBean();
    }

    @Test
    public void defaultSettingShouldValidateIPv4() throws Exception {
        ipBean.ip = "192.168.0.1";

        Set<ConstraintViolation<IPBean>> violations = validate("ip");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingShouldValidateIPv6() throws Exception {
        ipBean.ip = "fe80::8a1f:a1ff:fe11:9326";

        Set<ConstraintViolation<IPBean>> violations = validate("ip");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingShouldValidateNullString() throws Exception {
        ipBean.ip = null;

        Set<ConstraintViolation<IPBean>> violations = validate("ip");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingShouldValidateBlankString() throws Exception {
        ipBean.ip = " ";

        Set<ConstraintViolation<IPBean>> violations = validate("ip");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingShouldNotValidateRandomString() throws Exception {
        ipBean.ip = "abcd";

        Set<ConstraintViolation<IPBean>> violations = validate("ip");

        assertThat(violations, hasSize(1));
        ConstraintViolation<IPBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is((equalTo("must be an IP"))));
    }

    @Test
    public void defaultSettingShouldNotValidateInvalidIPv4() throws Exception {
        ipBean.ip = "999.168.0.1";

        Set<ConstraintViolation<IPBean>> violations = validate("ip");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void defaultSettingShouldNotValidateInvalidIPv6() throws Exception {
        ipBean.ip = "ge99::8a1f:a1ff:fe11:9326";

        Set<ConstraintViolation<IPBean>> violations = validate("ip");

        assertThat(violations, hasSize(1));
    }

    // IPv4

    @Test
    public void ipv4SettingShouldValidateIPv4() throws Exception {
        ipBean.ipv4 = "192.168.0.1";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv4");

        assertThat(violations, is(empty()));
    }

    @Test
    public void ipv4SettingShouldNotValidateIPv6() throws Exception {
        ipBean.ipv4 = "fe80::8a1f:a1ff:fe11:9326";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv4");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void ipv4SettingShouldValidateNullString() throws Exception {
        ipBean.ipv4 = null;

        Set<ConstraintViolation<IPBean>> violations = validate("ipv4");

        assertThat(violations, is(empty()));
    }

    @Test
    public void ipv4SettingShouldValidateBlankString() throws Exception {
        ipBean.ipv4 = " ";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv4");

        assertThat(violations, is(empty()));
    }

    @Test
    public void ipv4SettingShouldNotValidateRandomString() throws Exception {
        ipBean.ipv4 = "abcd";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv4");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void ipv4SettingShouldNotValidateInvalidIPv4() throws Exception {
        ipBean.ipv4 = "999.168.0.1";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv4");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void ipv4SettingShouldNotValidateInvalidIPv6() throws Exception {
        ipBean.ipv4 = "ge99::8a1f:a1ff:fe11:9326";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv4");

        assertThat(violations, hasSize(1));
    }

    // IPv6

    @Test
    public void ipv6SettingShouldNotValidateIPv4() throws Exception {
        ipBean.ipv6 = "192.168.0.1";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv6");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void ipv6SettingShouldNotValidateIPv6() throws Exception {
        ipBean.ipv6 = "fe80::8a1f:a1ff:fe11:9326";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv6");

        assertThat(violations, is(empty()));
    }

    @Test
    public void ipv6SettingShouldValidateNullString() throws Exception {
        ipBean.ipv6 = null;

        Set<ConstraintViolation<IPBean>> violations = validate("ipv6");

        assertThat(violations, is(empty()));
    }

    @Test
    public void ipv6SettingShouldValidateBlankString() throws Exception {
        ipBean.ipv6 = " ";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv6");

        assertThat(violations, is(empty()));
    }

    @Test
    public void ipv6SettingShouldNotValidateRandomString() throws Exception {
        ipBean.ipv6 = "abcd";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv6");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void ipv6SettingShouldNotValidateInvalidIPv4() throws Exception {
        ipBean.ipv6 = "999.168.0.1";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv6");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void ipv6SettingShouldNotValidateInvalidIPv6() throws Exception {
        ipBean.ipv6 = "ge99::8a1f:a1ff:fe11:9326";

        Set<ConstraintViolation<IPBean>> violations = validate("ipv6");

        assertThat(violations, hasSize(1));
    }

    private Set<ConstraintViolation<IPBean>> validate(String property) {
        return validator.validateProperty(ipBean, property);
    }

    private static final class IPBean {
        @IP
        private String ip;
        @IP(type = IPType.IP_V4)
        private String ipv4;
        @IP(type = IPType.IP_V6)
        private String ipv6;
    }
}
