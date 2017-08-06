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

import javax.validation.ValidationException;
import java.io.File;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class NotExecutableTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();
    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @Before
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must not be executable");
    }

    @Test
    public void shouldValidateNull() throws Exception {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateNonExecutableDirectory() throws Exception {
        fileBean.file = tmpFolder.newFolder();
        fileBean.file.setExecutable(false);

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateExecutableDirectory() throws Exception {
        fileBean.file = tmpFolder.newFolder();

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateNonExecutableFile() throws Exception {
        fileBean.file = tmpFolder.newFile();
        fileBean.file.setExecutable(false);

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateExecutableFile() throws Exception {
        fileBean.file = tmpFolder.newFile();
        fileBean.file.setExecutable(true);

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateUnexecutableNonExistingFile() throws Exception {
        fileBean.file = new File("/should/not/exist");

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        fileBean.path = " ";

        validator.validate("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExecutableDirectory() throws Exception {
        fileBean.path = tmpFolder.newFolder().getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExecutableFile() throws Exception {
        File file = tmpFolder.newFile();
        file.setExecutable(true);
        fileBean.path = file.getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldValidateStringRepresentingNonExecutableFile() throws Exception {
        fileBean.path = tmpFolder.newFile().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldValidateStringRepresentingNonExecutableDirectory() throws Exception {
        File dir = tmpFolder.newFolder();
        dir.setExecutable(false);
        fileBean.path = dir.getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenAppliedToOtherTypes() throws Exception {
        validator.validate("object");
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
