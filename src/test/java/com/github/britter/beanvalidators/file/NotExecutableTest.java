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

import javax.validation.ValidationException;
import java.io.File;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class NotExecutableTest extends BaseFileTest {

    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @BeforeEach
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must not be executable");
    }

    @Test
    public void shouldValidateNull() {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    // does does not seem to be possible to set a file not executable on the CI environment running on windows.
    @DisabledOnOs(OS.WINDOWS)
    @Test
    public void shouldValidateNonExecutableDirectory() {
        fileBean.file = dir();
        fileBean.file.setExecutable(false);

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateExecutableDirectory() {
        fileBean.file = dir();

        validator.assertViolation("file");
    }

    // does does not seem to be possible to set a file not executable on the CI environment running on windows.
    @DisabledOnOs(OS.WINDOWS)
    @Test
    public void shouldValidateNonExecutableFile() {
        fileBean.file = file();
        fileBean.file.setExecutable(false);

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateExecutableFile() {
        fileBean.file = file();
        fileBean.file.setExecutable(true);

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateUnexecutableNonExistingFile() {
        fileBean.file = NON_EXISTENT_FILE;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateBlankString() {
        fileBean.path = " ";

        validator.validate("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExecutableDirectory() {
        fileBean.path = dir().getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExecutableFile() {
        File file = file();
        file.setExecutable(true);
        fileBean.path = file.getAbsolutePath();

        validator.assertViolation("path");
    }

    // does does not seem to be possible to set a file not executable on the CI environment running on windows.
    @DisabledOnOs(OS.WINDOWS)
    @Test
    public void shouldValidateStringRepresentingNonExecutableFile() {
        fileBean.path = file().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    // does does not seem to be possible to set a file not executable on the CI environment running on windows.
    @DisabledOnOs(OS.WINDOWS)
    @Test
    public void shouldValidateStringRepresentingNonExecutableDirectory() {
        File dir = dir();
        dir.setExecutable(false);
        fileBean.path = dir.getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldThrowExceptionWhenAppliedToOtherTypes() {
        assertThrows(ValidationException.class, () -> validator.validate("object"));
    }

    private static final class FileBean {
        @NotExecutable
        private File file;
        @NotExecutable
        private String path;
        @NotExecutable
        private Object object = new Object();
    }
}
