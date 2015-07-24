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

public class ISBNTest {

    private Validator validator;
    private ISBNBean isbnBean;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        isbnBean = new ISBNBean();
    }

    @Test
    public void defaultSettingsShouldValidateISBN10() {
        isbnBean.isbn = "3551551677";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingsShouldValidateISBN13() {
        isbnBean.isbn = "978-3-55155-167-2";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingShouldNotValidateInvalidISBN() throws Exception {
        isbnBean.isbn = "abc";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn");

        assertThat(violations, hasSize(1));
        ConstraintViolation<ISBNBean> violation = getFirst(violations);
        assertThat(violation.getMessage(), is(equalTo("must be an ISBN")));
    }

    @Test
    public void defaultSettingShouldValidateNullValue() throws Exception {
        isbnBean.isbn = null;

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn");

        assertThat(violations, is(empty()));
    }

    @Test
    public void defaultSettingShouldValidateBlankValue() throws Exception {
        isbnBean.isbn = " ";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn");

        assertThat(violations, is(empty()));
    }

    @Test
    public void isbn10SettingShouldValidateISBN10() throws Exception {
        isbnBean.isbn10 = "3551551677";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn10");

        assertThat(violations, is(empty()));
    }

    @Test
    public void isbn10SettingShouldNotValidateISBN13() throws Exception {
        isbnBean.isbn10 = "978-3-55155-167-2";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn10");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void isbn10SettingShouldNotValidateInvalidISBN() throws Exception {
        isbnBean.isbn10 = "abc";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn10");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void isbn10SettingShouldValidateNullValue() throws Exception {
        isbnBean.isbn10 = null;

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn10");

        assertThat(violations, is(empty()));
    }

    @Test
    public void isbn10SettingShouldValidateBlankValue() throws Exception {
        isbnBean.isbn10 = " ";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn10");

        assertThat(violations, is(empty()));
    }

    @Test
    public void isbn13SettingShouldNotValidateISBN10() throws Exception {
        isbnBean.isbn13 = "3551551677";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn13");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void isbn13SettingShouldValidateISBN13() throws Exception {
        isbnBean.isbn10 = "978-3-55155-167-2";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn13");

        assertThat(violations, is(empty()));
    }

    @Test
    public void isbn13SettingShouldNotValidateInvalidISBN() throws Exception {
        isbnBean.isbn13 = "abc";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn13");

        assertThat(violations, hasSize(1));
    }

    @Test
    public void isbn13SettingShouldValidateNullValue() throws Exception {
        isbnBean.isbn13 = null;

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn13");

        assertThat(violations, is(empty()));
    }
    @Test
    public void isbn13SettingShouldValidateBlankValue() throws Exception {
        isbnBean.isbn13 = " ";

        Set<ConstraintViolation<ISBNBean>> violations = validate("isbn13");

        assertThat(violations, is(empty()));
    }

    private Set<ConstraintViolation<ISBNBean>> validate(String property) {
        return validator.validateProperty(isbnBean, property);
    }

    private static <T> T getFirst(Set<T> set) {
        return set.iterator().next();
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
