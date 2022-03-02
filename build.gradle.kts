/*
 * Copyright 2019 Benedikt Ritter
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
description = "Additional validator implementations for javax.validation"
group = "com.github.britter"
version = "0.8.1-SNAPSHOT"

plugins {
    id("beanvalidators.java-conventions")
    id("beanvalidators.coveralls-conventions")
    id("beanvalidators.documentation-conventions")
    id("beanvalidators.publishing-conventions")
    id("beanvalidators.code-quality-conventions")
    id("org.nosphere.gradle.github.actions") version "1.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.javax.validation)
    implementation(libs.commons.lang)
    implementation(libs.commons.validator)

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.hibernate.validator)

    testFixturesApi(libs.assertj.core)
    testFixturesApi(libs.javax.elApi)
    testFixturesImplementation(libs.guava)
    testFixturesImplementation(libs.javax.jaxbApi)
}

tasks {
    val testTasks = listOf(8, 11, 17).map {
        register<Test>("testJdk$it") {
            javaLauncher.set(project.javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(it))
            })
        }
    }

    val testAll by registering {
        dependsOn(test, testTasks)
    }

    withType<Test> {
        useJUnitPlatform()
    }

    check {
        dependsOn(testAll)
    }
}
