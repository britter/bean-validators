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
import java.nio.charset.StandardCharsets

description = "Additional validator implementations for javax.validation"
group = "com.github.britter"
version = "0.6.4-SNAPSHOT"

plugins {
    `java-library`
    jacoco
    id("com.github.kt3k.coveralls") version "2.9.0"
    id("org.asciidoctor.convert") version "2.3.0"
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    withType<JavaCompile> {
        options.encoding = StandardCharsets.UTF_8.name()
    }

    val jacocoTestReport = named<JacocoReport>("jacocoTestReport") {
        reports {
            xml.isEnabled = true
        }
    }

    coveralls {
        jacocoReportPath = jacocoTestReport.map { it.reports.xml.destination }
    }
}

dependencies {
    api("javax.validation:validation-api:1.1.0.Final")
    implementation("org.apache.commons:commons-lang3:3.6")
    implementation("commons-validator:commons-validator:1.6")

    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj:assertj-core:3.8.0")
    testImplementation("com.google.guava:guava:23.0")
    testImplementation("org.hibernate:hibernate-validator:5.1.3.Final")
    testImplementation("javax.el:javax.el-api:3.0.0")
}
