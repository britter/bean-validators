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
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

import javax.validation.ValidationException;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ExecutableTest extends BaseFileTest {

    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @BeforeEach
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must be executable");
    }

    @Test
    public void shouldValidateNull() {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateExecutableDirectory() {
        fileBean.file = dir();

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateExecutableFile() {
        fileBean.file = file();
        fileBean.file.setExecutable(true);

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateUnexecutableFile() {
        fileBean.file = NON_EXISTENT_FILE;

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateStringRepresentingExecutableDirectory() {
        fileBean.path = dir().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldValidateStringRepresentingExecutableFile() {
        File file = file();
        file.setExecutable(true);
        fileBean.path = file.getAbsolutePath();

        validator.assertNoViolations("path");
    }

    // does does not seem to be possible to set a file not executable on the CI environment running on windows.
    @DisabledOnOs(OS.WINDOWS)
    @Test
    public void shouldNotValidateStringRepresentingNonExecutableFile() {
        File file = file();
        file.setExecutable(false);
        fileBean.path = file.getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldThrowExceptionWhenOtherTypeIsAnnotated() {
        assertThrows(ValidationException.class, () -> validator.validate("object"));
    }

    private static final class FileBean {
        @Executable
        private File file;
        @Executable
        private String path;
        @Executable
        private Object object = new Object();
    }
}
