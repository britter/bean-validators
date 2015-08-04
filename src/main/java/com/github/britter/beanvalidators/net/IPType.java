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

/**
 * Enum controlling the behavior of {@link IP @IP}.
 *
 * @since 0.3
 */
public enum IPType {

    /**
     * Only validate IPv4 addresses. These are addresses of the kind: {@çode 192.168.0.1}.
     */
    IP_V4,
    /**
     * Only validate IPv6 addresses. These are addresses of the kind: {@çode fe80::8a1f:a1ff:fe11:9326}.
     */
    IP_V6,
    /**
     * Validate both, IPv4 and IPv6 addresses.
     */
    ALL
}
