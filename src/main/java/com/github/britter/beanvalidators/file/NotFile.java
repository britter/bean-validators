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

import com.github.britter.beanvalidators.file.internal.NotFileFileConstraintValidator;
import com.github.britter.beanvalidators.file.internal.NotFileStringFileConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Makes sure the validated file or path does not represent a file in the file system.
 *
 * <p>
 * Note that both {@link java.io.File#isFile()} and {@link java.io.File#isDirectory()} can return false, for example for
 * file objects representing sockets, mounts or pipes.
 * </p>
 *
 * @see java.io.File#isFile()
 * @since 0.5.0
 */
@Target({METHOD, FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {
        NotFileFileConstraintValidator.class,
        NotFileStringFileConstraintValidator.class,
})
@Documented
public @interface NotFile {

    String message() default "{com.github.britter.beanvalidators.file.NotFile.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
