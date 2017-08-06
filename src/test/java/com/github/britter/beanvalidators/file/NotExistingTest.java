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

public final class NotExistingTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();
    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @Before
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must not exist");
    }

    @Test
    public void shouldValidateNull() throws Exception {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateDirectory() throws Exception {
        fileBean.file = tmpFolder.newFolder();

        validator.assertViolation("file");
    }

    @Test
    public void shouldNotValidateFile() throws Exception {
        fileBean.file = tmpFolder.newFile();

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateNonExistingFile() throws Exception {
        fileBean.file = new File("/should/not/exist");

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        fileBean.path = " ";

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExistingDirectory() throws Exception {
        fileBean.path = tmpFolder.newFolder().getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingExistingFile() throws Exception {
        fileBean.path = tmpFolder.newFile().getAbsolutePath();

        validator.assertViolation("path");
    }

    @Test
    public void shouldValidateStringRepresentingNonExistingFile() throws Exception {
        fileBean.path = "/should/not/exist";

        validator.assertNoViolations("path");
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenAppliedToOtherTypes() throws Exception {
        validator.validate("object");
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
