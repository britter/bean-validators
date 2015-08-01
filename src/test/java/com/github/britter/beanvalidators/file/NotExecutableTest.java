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

import java.io.File;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class NotExecutableTest {

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

    private static final class FileBean {
        @NotExecutable
        private File file;
    }
}