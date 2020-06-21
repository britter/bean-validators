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
package com.github.britter.beanvalidators.file;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NotExistingTest extends BaseFileTest {

    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @BeforeEach
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must not exist");
    }

    @Test
    public void shouldValidateNull() {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateDirectory() {
        fileBean.file = dir();

        validator.assertViolation("file");
    }

    @Test
    public void shouldNotValidateFile() {
        fileBean.file = file();

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateNonExistingFile() {
        fileBean.file = NON_EXISTENT_FILE;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateBlankString() {
        fileBean.path = " ";

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExistingDirectory() {
        fileBean.path = dir().getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExistingFile() {
        fileBean.path = file().getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldValidateStringRepresentingNonExistingFile() {
        fileBean.path = NON_EXISTENT_PATH;;

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldThrowExceptionWhenAppliedToOtherTypes() {
        assertThrows(ValidationException.class, () -> validator.validate("object"));
    }

    private static final class FileBean {
        @NotExisting
        private File file;
        @NotExisting
        private String path;
        @NotExisting
        private Object object = new Object();
    }
}
