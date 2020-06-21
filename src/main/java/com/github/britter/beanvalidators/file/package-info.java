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
/**
 * Constraints for validating all properties of {@link java.io.File} objects.
 *
 * <p>
 * There is an annotation for each property as well as one for the negation of each property.
 * All annotations can also be applied to Strings. The validators will construct a new File instance using
 * {@link java.io.File#File(java.lang.String)} and use that instance for validation.
 * </p>
 *
 * @since 0.5.0
 */
package com.github.britter.beanvalidators.file;
