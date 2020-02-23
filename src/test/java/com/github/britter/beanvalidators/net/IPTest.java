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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public final class IPTest {

    private IPBean ipBean;
    private ValidationWrapper<IPBean> validator;

    @BeforeEach
    public void setUp() {
        ipBean = new IPBean();
        validator = new ValidationWrapper<>(ipBean, "must be an IP");
    }

    @Test
    public void defaultSettingShouldValidateIPv4() {
        ipBean.ip = "192.168.0.1";

        validator.assertNoViolations("ip");
    }

    @Test
    public void defaultSettingShouldValidateIPv6() {
        ipBean.ip = "fe80::8a1f:a1ff:fe11:9326";

        validator.assertNoViolations("ip");
    }

    @Test
    public void defaultSettingShouldValidateNullString() {
        ipBean.ip = null;

        validator.assertNoViolations("ip");
    }

    @Test
    public void defaultSettingShouldValidateBlankString() {
        ipBean.ip = " ";

        validator.assertNoViolations("ip");
    }

    @Test
    public void defaultSettingShouldNotValidateRandomString() {
        ipBean.ip = "abcd";

        validator.assertViolation("ip");
    }

    @Test
    public void defaultSettingShouldNotValidateInvalidIPv4() {
        ipBean.ip = "999.168.0.1";

        validator.assertViolation("ip");
    }

    @Test
    public void defaultSettingShouldNotValidateInvalidIPv6() {
        ipBean.ip = "ge99::8a1f:a1ff:fe11:9326";

        validator.assertViolation("ip");
    }

    @Nested
    class IPv4 {

        @Test
        public void ipv4SettingShouldValidateIPv4() {
            ipBean.ipv4 = "192.168.0.1";

            validator.assertNoViolations("ipv4");
        }

        @Test
        public void ipv4SettingShouldNotValidateIPv6() {
            ipBean.ipv4 = "fe80::8a1f:a1ff:fe11:9326";

            validator.assertViolation("ipv4");
        }

        @Test
        public void ipv4SettingShouldValidateNullString() {
            ipBean.ipv4 = null;

            validator.assertNoViolations("ipv4");
        }

        @Test
        public void ipv4SettingShouldValidateBlankString() {
            ipBean.ipv4 = " ";

            validator.assertNoViolations("ipv4");
        }

        @Test
        public void ipv4SettingShouldNotValidateRandomString() {
            ipBean.ipv4 = "abcd";

            validator.assertViolation("ipv4");
        }

        @Test
        public void ipv4SettingShouldNotValidateInvalidIPv4() {
            ipBean.ipv4 = "999.168.0.1";

            validator.assertViolation("ipv4");
        }

        @Test
        public void ipv4SettingShouldNotValidateInvalidIPv6() {
            ipBean.ipv4 = "ge99::8a1f:a1ff:fe11:9326";

            validator.assertViolation("ipv4");
        }
    }

    @Nested
    class IPv6 {

        @Test
        public void ipv6SettingShouldNotValidateIPv4() {
            ipBean.ipv6 = "192.168.0.1";

            validator.assertViolation("ipv6");
        }

        @Test
        public void ipv6SettingShouldNotValidateIPv6() {
            ipBean.ipv6 = "fe80::8a1f:a1ff:fe11:9326";

            validator.assertNoViolations("ipv6");
        }

        @Test
        public void ipv6SettingShouldValidateNullString() {
            ipBean.ipv6 = null;

            validator.assertNoViolations("ipv6");
        }

        @Test
        public void ipv6SettingShouldValidateBlankString() {
            ipBean.ipv6 = " ";

            validator.assertNoViolations("ipv6");
        }

        @Test
        public void ipv6SettingShouldNotValidateRandomString() {
            ipBean.ipv6 = "abcd";

            validator.assertViolation("ipv6");
        }

        @Test
        public void ipv6SettingShouldNotValidateInvalidIPv4() {
            ipBean.ipv6 = "999.168.0.1";

            validator.assertViolation("ipv6");
        }

        @Test
        public void ipv6SettingShouldNotValidateInvalidIPv6() {
            ipBean.ipv6 = "ge99::8a1f:a1ff:fe11:9326";

            validator.assertViolation("ipv6");
        }
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
