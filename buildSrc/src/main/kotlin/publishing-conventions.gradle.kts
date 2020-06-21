import java.time.Duration

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
plugins {
    `maven-publish`
    signing
    id("de.marcphilipp.nexus-publish")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set(provider { project.name })
                description.set(provider { project.description })
                url.set("https://britter.github.io/bean-validators")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
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
    connectTimeout.set(Duration.ofMinutes(5))
    clientTimeout.set(Duration.ofMinutes(5))
    repositories {
        sonatype()
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}
