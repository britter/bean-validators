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
package com.github.britter.beanvalidators.file;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ReadableTest extends BaseFileTest {

    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @BeforeEach
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must be readable");
    }

    @Test
    public void shouldValidateNull() {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateReadableDirectory() {
        fileBean.file = dir();

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateReadableFile() {
        fileBean.file = file();

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateUnreadableFile() {
        fileBean.file = NON_EXISTENT_FILE;

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateStringRepresentingReadableDirectory() {
        fileBean.path = dir().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldValidateStringRepresentingReadableFile() {
        fileBean.path = file().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingNonExistingFile() {
        fileBean.path = NON_EXISTENT_PATH;;

        validator.assertViolation("path");
    }

    @Test
    public void shouldThrowExceptionWhenAppliedToOtherTypes() {
        assertThrows(ValidationException.class, () -> validator.validate("object"));
    }

    private static final class FileBean {
        @Readable
        private File file;
        @Readable
        private String path;
        @Readable
        private Object object = new Object();
    }
}
