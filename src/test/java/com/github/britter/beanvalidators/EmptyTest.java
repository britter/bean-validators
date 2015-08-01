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
package com.github.britter.beanvalidators;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class EmptyTest {

    private EmptyBean emptyBean;
    private ValidationWrapper<EmptyBean> validator;

    @Before
    public void setUp() throws Exception {
        emptyBean = new EmptyBean();
        validator = new ValidationWrapper<>(emptyBean, "must be empty");
    }

    // Strings

    @Test
    public void shouldValidateEmptyString() throws Exception {
        emptyBean.string = "";

        validator.assertNoViolations("string");
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        emptyBean.string = null;

        validator.assertNoViolations("string");
    }

    @Test
    public void shouldNotValidateBlankString() throws Exception {
        emptyBean.string = "  ";

        validator.assertViolation("string");
    }

    @Test
    public void shouldNotValidateString() throws Exception {
        emptyBean.string = "abcd";

        validator.assertViolation("string");
    }

    // Collections

    @Test
    public void shouldValidateNullCollection() throws Exception {
        emptyBean.col = null;

        validator.assertNoViolations("col");
    }

    @Test
    public void shouldValidateEmptyCollection() throws Exception {
        emptyBean.col = Collections.emptyList();

        validator.assertNoViolations("col");
    }

    @Test
    public void shouldNotValidateFilledCollection() throws Exception {
        emptyBean.col = Collections.singleton("abcd");

        validator.assertViolation("col");
    }

    // Maps

    @Test
    public void shouldValidateNullMap() throws Exception {
        emptyBean.map = null;

        validator.assertNoViolations("map");
    }

    @Test
    public void shouldValidateEmptyMap() throws Exception {
        emptyBean.map = Collections.emptyMap();

        validator.assertNoViolations("map");
    }

    @Test
    public void shouldNotValidateFilledMap() throws Exception {
        emptyBean.map = Collections.singletonMap("key", "value");

        validator.assertViolation("map");
    }

    // Arrays

    @Test
    public void shouldValidateNullArray() throws Exception {
        emptyBean.array = null;

        validator.assertNoViolations("array");
    }

    @Test
    public void shouldValidateEmptyArray() throws Exception {
        emptyBean.array = new String[0];

        validator.assertNoViolations("array");
    }

    @Test
    public void shouldNotValidateNonEmptyArray() throws Exception {
        emptyBean.array = new String[1];

        validator.assertViolation("array");
    }

    // Other
    @Test(expected = ValidationException.class)
    public void shouldName() throws Exception {
        emptyBean.integer = 1;

        validator.validate("integer");
    }

    private static final class EmptyBean {
        @Empty
        private String string;
        @Empty
        private Collection<String> col;
        @Empty
        private Map<String, String> map;
        @Empty
        private String[] array;
        @Empty
        private Integer integer;
    }
}
