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
package com.github.britter.beanvalidators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class EmptyTest {

    private EmptyBean emptyBean;
    private ValidationWrapper<EmptyBean> validator;

    @BeforeEach
    public void setUp() {
        emptyBean = new EmptyBean();
        validator = new ValidationWrapper<>(emptyBean, "must be empty");
    }

    @Nested
    class WithStrings {

        @Test
        public void shouldValidateEmptyString() {
            emptyBean.string = "";

            validator.assertNoViolations("string");
        }

        @Test
        public void shouldValidateNullString() {
            emptyBean.string = null;

            validator.assertNoViolations("string");
        }

        @Test
        public void shouldNotValidateBlankString() {
            emptyBean.string = "  ";

            validator.assertViolation("string");
        }

        @Test
        public void shouldNotValidateString() {
            emptyBean.string = "abcd";

            validator.assertViolation("string");
        }
    }

    @Nested
    class WithCollections {

        @Test
        public void shouldValidateNullCollection() {
            emptyBean.col = null;

            validator.assertNoViolations("col");
        }

        @Test
        public void shouldValidateEmptyCollection() {
            emptyBean.col = Collections.emptyList();

            validator.assertNoViolations("col");
        }

        @Test
        public void shouldNotValidateFilledCollection() {
            emptyBean.col = Collections.singleton("abcd");

            validator.assertViolation("col");
        }
    }

    @Nested
    class WithMaps {

        @Test
        public void shouldValidateNullMap() {
            emptyBean.map = null;

            validator.assertNoViolations("map");
        }

        @Test
        public void shouldValidateEmptyMap() {
            emptyBean.map = Collections.emptyMap();

            validator.assertNoViolations("map");
        }

        @Test
        public void shouldNotValidateFilledMap() {
            emptyBean.map = Collections.singletonMap("key", "value");

            validator.assertViolation("map");
        }
    }

    @Nested
    class WithArrays {

        @Test
        public void shouldValidateNullArray() {
            emptyBean.array = null;

            validator.assertNoViolations("array");
        }

        @Test
        public void shouldValidateEmptyArray() {
            emptyBean.array = new String[0];

            validator.assertNoViolations("array");
        }

        @Test
        public void shouldNotValidateNonEmptyArray() {
            emptyBean.array = new String[1];

            validator.assertViolation("array");
        }
    }

    @Nested
    class WithOptional {

        @Test
        public void shouldValidateNullOptional() {
            emptyBean.optional = null;

            validator.assertNoViolations("optional");
        }

        @Test
        public void shouldValidateEmptyOptional() {
            emptyBean.optional = Optional.empty();

            validator.assertNoViolations("optional");
        }

        @Test
        public void shouldNotValidateNonEmptyOptional() {
            emptyBean.optional = Optional.of("some");

            validator.assertViolation("optional");
        }
    }

    @Test
    public void shouldName() {
        emptyBean.integer = 1;

        assertThrows(ValidationException.class, () -> validator.validate("integer"));
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
        @Empty
        private Optional<String> optional;
    }
}
