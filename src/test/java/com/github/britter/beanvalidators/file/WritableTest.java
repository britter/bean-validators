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

public class WritableTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();
    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @Before
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must be writable");
    }

    @Test
    public void shouldValidateNull() throws Exception {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateWritableDirectory() throws Exception {
        fileBean.file = tmpFolder.newFolder();

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateWriteableFile() throws Exception {
        fileBean.file = tmpFolder.newFile();

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldNotValidateUnwritableFile() throws Exception {
        fileBean.file = new File("/should/not/exist");

        validator.assertViolation("file");
    }

    @Test
    public void shouldValidateBlankStrings() throws Exception {
        fileBean.path = " ";

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldValidateStringRepresentingWritableDirectory() throws Exception {
        fileBean.path = tmpFolder.newFolder().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldValidateStringRepresentingWritableFile() throws Exception {
        fileBean.path = tmpFolder.newFile().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingNonExistingFile() throws Exception {
        fileBean.path = "/should/not/exist";

        validator.assertViolation("path");
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenAppliedToOtherTypes() throws Exception {
        validator.validate("object");
    }

    private static final class FileBean {
        @Writable
        private File file;
        @Writable
        private String path;
        @Writable
        private Object object = new Object();
    }
}