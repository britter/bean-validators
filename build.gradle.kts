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
    `java-test-fixtures`
    jacoco
    `maven-publish`
    signing
    id("de.marcphilipp.nexus-publish") version "0.3.0"
    id("com.github.kt3k.coveralls") version "2.9.0"
    id("org.asciidoctor.jvm.convert") version "2.4.0"
    id("org.ajoberstar.git-publish") version "2.1.3"
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

tasks {
    withType<JavaCompile> {
        options.encoding = StandardCharsets.UTF_8.name()
    }

    javadoc {
        exclude("**/internal/**")
    }

    val jacocoTestReport = named<JacocoReport>("jacocoTestReport") {
        reports {
            xml.isEnabled = true
        }
    }

    coveralls {
        jacocoReportPath = jacocoTestReport.map { it.reports.xml.destination }
    }

    asciidoctor {
        outputOptions {
            separateOutputDirs = false
        }

        attributes(mapOf(
                "source-highlighter" to "coderay",
                "tabsize" to "4",
                "toc" to "left",
                "icons" to "font",
                "sectanchors" to true,
                "idprefix" to "",
                "idseparator" to "-"
        ))
    }
}

dependencies {
    api("javax.validation:validation-api:1.1.0.Final")
    implementation("org.apache.commons:commons-lang3:3.6")
    implementation("commons-validator:commons-validator:1.6")

    testImplementation("junit:junit:4.12")
    testRuntimeOnly("org.hibernate:hibernate-validator:5.1.3.Final")

    testFixturesApi("org.assertj:assertj-core:3.8.0")
    testFixturesApi("javax.el:javax.el-api:3.0.0")
    testFixturesImplementation("com.google.guava:guava:23.0")
}

val asciidoctor by tasks.getting
val javadoc by tasks.getting
val testFixturesJavadoc by tasks.getting

gitPublish {
    repoUri.set("https://github.com/britter/bean-validators")
    branch.set("gh-pages")

    contents {
        from(asciidoctor)
        from(javadoc) {
            into("apidocs/main")
        }
        from(testFixturesJavadoc) {
            into("apidocs/test-fixtures")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                description.set(project.description)
                url.set("https://britter.github.io/bean-validators")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("britter")
                        name.set("Benedikt Ritter")
                        email.set("beneritter@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/britter/bean-validators.git")
                    developerConnection.set("scm:git:ssh://git@github.com/britter/bean-validators.git")
                    url.set("https://github.com/britter/bean-validators")
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}
