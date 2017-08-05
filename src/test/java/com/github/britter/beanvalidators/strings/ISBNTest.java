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
import org.junit.Before;
import org.junit.Test;

public class ISBNTest {

    private ValidationWrapper<ISBNBean> validator;
    private ISBNBean isbnBean;

    @Before
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
    public void defaultSettingShouldNotValidateInvalidISBN() throws Exception {
        isbnBean.isbn = "abc";

        validator.assertViolation("isbn");
    }

    @Test
    public void defaultSettingShouldValidateNullValue() throws Exception {
        isbnBean.isbn = null;

        validator.assertNoViolations("isbn");
    }

    @Test
    public void defaultSettingShouldValidateBlankValue() throws Exception {
        isbnBean.isbn = " ";

        validator.assertNoViolations("isbn");
    }

    @Test
    public void isbn10SettingShouldValidateISBN10() throws Exception {
        isbnBean.isbn10 = "3551551677";

        validator.assertNoViolations("isbn10");
    }

    @Test
    public void isbn10SettingShouldNotValidateISBN13() throws Exception {
        isbnBean.isbn10 = "978-3-55155-167-2";

        validator.assertViolation("isbn10");
    }

    @Test
    public void isbn10SettingShouldNotValidateInvalidISBN() throws Exception {
        isbnBean.isbn10 = "abc";

        validator.assertViolation("isbn10");
    }

    @Test
    public void isbn10SettingShouldValidateNullValue() throws Exception {
        isbnBean.isbn10 = null;

        validator.assertNoViolations("isbn10");
    }

    @Test
    public void isbn10SettingShouldValidateBlankValue() throws Exception {
        isbnBean.isbn10 = " ";

        validator.assertNoViolations("isbn10");
    }

    @Test
    public void isbn13SettingShouldNotValidateISBN10() throws Exception {
        isbnBean.isbn13 = "3551551677";

        validator.assertViolation("isbn13");
    }

    @Test
    public void isbn13SettingShouldValidateISBN13() throws Exception {
        isbnBean.isbn10 = "978-3-55155-167-2";

        validator.assertNoViolations("isbn13");
    }

    @Test
    public void isbn13SettingShouldNotValidateInvalidISBN() throws Exception {
        isbnBean.isbn13 = "abc";

        validator.assertViolation("isbn13");
    }

    @Test
    public void isbn13SettingShouldValidateNullValue() throws Exception {
        isbnBean.isbn13 = null;

        validator.assertNoViolations("isbn13");
    }

    @Test
    public void isbn13SettingShouldValidateBlankValue() throws Exception {
        isbnBean.isbn13 = " ";

        validator.assertNoViolations("isbn13");
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
