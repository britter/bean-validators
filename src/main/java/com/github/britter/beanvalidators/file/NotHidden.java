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

import com.github.britter.beanvalidators.file.internal.NotHiddenFileConstraintValidator;
import com.github.britter.beanvalidators.file.internal.NotHiddenStringFileConstraintValidator;

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
 * Makes sure the validated file or path is not hidden.
 *
 * <p>
 * Note that the definition of a hidden files various between operating systems. For examples in an UNIX-like OS, all
 * files and directories starting with a dot are considered to be hidden, whereas on Windows the hidden attribute of the
 * file has to be set to true.
 * </p>
 *
 * @see java.io.File#isHidden()
 * @since 0.5.0
 */
@Target({METHOD, FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {
        NotHiddenFileConstraintValidator.class,
        NotHiddenStringFileConstraintValidator.class,
})
@Documented
public @interface NotHidden {

    String message() default "{com.github.britter.beanvalidators.file.NotHidden.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
