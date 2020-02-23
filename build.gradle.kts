/*
 * Copyright 2019 Benedikt Ritter
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
description = "Additional validator implementations for javax.validation"
group = "com.github.britter"
version = "0.7.1-SNAPSHOT"

plugins {
    `java-conventions`
    `coveralls-conventions`
    `documentation-conventions`
    `publishing-conventions`
}

repositories {
    mavenCentral()
}

dependencies {
    api("javax.validation:validation-api:1.1.0.Final")
    implementation("org.apache.commons:commons-lang3:3.6")
    implementation("commons-validator:commons-validator:1.6")

    testImplementation("junit:junit:4.12")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.5.2")
    testRuntimeOnly("org.hibernate:hibernate-validator:5.1.3.Final")

    testFixturesApi("org.assertj:assertj-core:3.8.0")
    testFixturesApi("javax.el:javax.el-api:3.0.0")
    testFixturesImplementation("com.google.guava:guava:23.0")
    testFixturesImplementation("javax.xml.bind:jaxb-api:2.3.1")
}

tasks.test {
    useJUnitPlatform()
}
