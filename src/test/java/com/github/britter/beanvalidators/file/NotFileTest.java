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

public class NotFileTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();
    private FileBean fileBean;
    private ValidationWrapper<FileBean> validator;

    @Before
    public void setUp() {
        fileBean = new FileBean();
        validator = new ValidationWrapper<>(fileBean, "must not be a file");
    }

    @Test
    public void shouldValidateNull() throws Exception {
        fileBean.file = null;

        validator.assertNoViolations("file");
    }

    @Test
    public void shouldValidateDirectory() throws Exception {
        fileBean.file = tmpFolder.newFolder();

        validator.assertNoViolations("file");
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
    public void shouldValidateStringRepresentingDirectory() throws Exception {
        fileBean.path = tmpFolder.newFolder().getAbsolutePath();

        validator.assertNoViolations("path");
    }

    @Test
    public void shouldNotValidateStringRepresentingFile() throws Exception {
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
        @NotFile
        private File file;
        @NotFile
        private String path;
        @NotFile
        private Object object = new Object();
    }
}