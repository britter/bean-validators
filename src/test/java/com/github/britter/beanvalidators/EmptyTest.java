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

import static com.google.common.collect.Iterables.getLast;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class EmptyTest {

    private EmptyBean emptyBean;
    private ValidationWrapper<EmptyBean> validator;

    @Before
    public void setUp() throws Exception {
        emptyBean = new EmptyBean();
        validator = new ValidationWrapper<>(emptyBean);
    }

    // Strings

    @Test
    public void shouldValidateEmptyString() throws Exception {
        emptyBean.string = "";

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("string");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateNullString() throws Exception {
        emptyBean.string = null;

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("string");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateBlankString() throws Exception {
        emptyBean.string = "  ";

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("string");

        assertThat(violations, hasSize(1));
        ConstraintViolation<EmptyBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be empty")));
    }

    @Test
    public void shouldNotValidateString() throws Exception {
        emptyBean.string = "abcd";

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("string");

        assertThat(violations, hasSize(1));
        ConstraintViolation<EmptyBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be empty")));
    }

    // Collections

    @Test
    public void shouldValidateNullCollection() throws Exception {
        emptyBean.col = null;

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("col");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateEmptyCollection() throws Exception {
        emptyBean.col = Collections.emptyList();

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("col");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateFilledCollection() throws Exception {
        emptyBean.col = Collections.singleton("abcd");

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("col");

        assertThat(violations, hasSize(1));
    }

    // Maps

    @Test
    public void shouldValidateNullMap() throws Exception {
        emptyBean.map = null;

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("map");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateEmptyMap() throws Exception {
        emptyBean.map = Collections.emptyMap();

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("map");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateFilledMap() throws Exception {
        emptyBean.map = Collections.singletonMap("key", "value");

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("map");

        assertThat(violations, hasSize(1));
    }

    // Arrays

    @Test
    public void shouldValidateNullArray() throws Exception {
        emptyBean.array = null;

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("array");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldValidateEmptyArray() throws Exception {
        emptyBean.array = new String[0];

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("array");

        assertThat(violations, is(empty()));
    }

    @Test
    public void shouldNotValidateNonEmptyArray() throws Exception {
        emptyBean.array = new String[1];

        Set<ConstraintViolation<EmptyBean>> violations = validator.validate("array");

        assertThat(violations, hasSize(1));
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
