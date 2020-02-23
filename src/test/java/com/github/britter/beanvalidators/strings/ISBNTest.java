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
package com.github.britter.beanvalidators.strings;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public final class ISBNTest {

    private ValidationWrapper<ISBNBean> validator;
    private ISBNBean isbnBean;

    @BeforeEach
    public void setUp() {
        isbnBean = new ISBNBean();
        validator = new ValidationWrapper<>(isbnBean, "must be an ISBN");
    }

    @Test
    public void defaultSettingsShouldValidateISBN10() {
        isbnBean.isbn = "3551551677";

        validator.assertNoViolations("isbn");
    }

    @Test
    public void defaultSettingsShouldValidateISBN13() {
        isbnBean.isbn = "978-3-55155-167-2";

        validator.assertNoViolations("isbn");
    }

    @Test
    public void defaultSettingShouldNotValidateInvalidISBN() {
        isbnBean.isbn = "abc";

        validator.assertViolation("isbn");
    }

    @Test
    public void defaultSettingShouldValidateNullValue() {
        isbnBean.isbn = null;

        validator.assertNoViolations("isbn");
    }

    @Test
    public void defaultSettingShouldValidateBlankValue() {
        isbnBean.isbn = " ";

        validator.assertNoViolations("isbn");
    }

    @Nested
    class Isbn10 {

        @Test
        public void isbn10SettingShouldValidateISBN10() {
            isbnBean.isbn10 = "3551551677";

            validator.assertNoViolations("isbn10");
        }

        @Test
        public void isbn10SettingShouldNotValidateISBN13() {
            isbnBean.isbn10 = "978-3-55155-167-2";

            validator.assertViolation("isbn10");
        }

        @Test
        public void isbn10SettingShouldNotValidateInvalidISBN() {
            isbnBean.isbn10 = "abc";

            validator.assertViolation("isbn10");
        }

        @Test
        public void isbn10SettingShouldValidateNullValue() {
            isbnBean.isbn10 = null;

            validator.assertNoViolations("isbn10");
        }

        @Test
        public void isbn10SettingShouldValidateBlankValue() {
            isbnBean.isbn10 = " ";

            validator.assertNoViolations("isbn10");
        }
    }

    @Nested
    class Isbn13 {

        @Test
        public void isbn13SettingShouldNotValidateISBN10() {
            isbnBean.isbn13 = "3551551677";

            validator.assertViolation("isbn13");
        }

        @Test
        public void isbn13SettingShouldValidateISBN13() {
            isbnBean.isbn10 = "978-3-55155-167-2";

            validator.assertNoViolations("isbn13");
        }

        @Test
        public void isbn13SettingShouldNotValidateInvalidISBN() {
            isbnBean.isbn13 = "abc";

            validator.assertViolation("isbn13");
        }

        @Test
        public void isbn13SettingShouldValidateNullValue() {
            isbnBean.isbn13 = null;

            validator.assertNoViolations("isbn13");
        }

        @Test
        public void isbn13SettingShouldValidateBlankValue() {
            isbnBean.isbn13 = " ";

            validator.assertNoViolations("isbn13");
        }
    }

    private static final class ISBNBean {
        @ISBN
        private String isbn;

        @ISBN(type = ISBNType.ISBN_10)
        private String isbn10;

        @ISBN(type = ISBNType.ISBN_13)
        private String isbn13;
    }
}
