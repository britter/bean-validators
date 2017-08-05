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

import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.io.File;
import java.lang.annotation.Annotation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFileConstraintValidatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    private AbstractFileConstraintValidatorImpl validator;

    @Before
    public void setUp() throws Exception {
        validator = new AbstractFileConstraintValidatorImpl();
        validator.initialize(new TestAnnotation());
    }

    @Test
    public void shouldValidateNull() throws Exception {
        assertThat(validator.isValid((Object) null, null)).isTrue();
    }

    @Test
    public void shouldPassFileObjectsToSubclass() throws Exception {
        File file = tmpFolder.newFile();
        validator.isValid(((Object) file), null);

        assertThat(validator.validatedFile).isEqualTo(file);
    }

    @Test
    public void shouldValidateBlankString() throws Exception {
        assertThat(validator.isValid(" ", null)).isTrue();
    }

    @Test
    public void shouldNotPassAnythingToSubclassForBlankString() throws Exception {
        validator.isValid(" ", null);

        assertThat(validator.validatedFile).isNull();
    }

    @Test
    public void shouldConstructFileInstanceFromString() throws Exception {
        validator.isValid("/test/file", null);

        assertThat(validator.validatedFile.getAbsolutePath()).isEqualTo("/test/file");
    }

    @Test
    public void shouldConstructErrorMessageWhenDifferentTypeIsAnnotated() throws Exception {
        thrown.expect(ValidationException.class);
        thrown.expectMessage("@Absolute can not be applied to instances of class java.lang.Object");

        validator.isValid(new Object(), null);
    }

    private static final class AbstractFileConstraintValidatorImpl extends AbstractFileConstraintValidator<TestAnnotation> {
        private File validatedFile;

        @Override
        public boolean isValid(File value, ConstraintValidatorContext context) {
            this.validatedFile = value;
            return true;
        }
    }

    private static final class TestAnnotation implements Annotation {
        @Override
        public Class<? extends Annotation> annotationType() {
            return Absolute.class;
        }
    }
}
