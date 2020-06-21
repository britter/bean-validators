import org.ajoberstar.gradle.git.publish.tasks.GitPublishCommit
import org.ajoberstar.gradle.git.publish.tasks.GitPublishReset
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
    id("org.asciidoctor.jvm.convert")
    id("org.ajoberstar.git-publish")
}

tasks {
    named("javadoc", Javadoc::class) {
        exclude("**/internal/**")
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

val asciidoctor by tasks.getting
val javadoc by tasks.getting
val testFixturesJavadoc by tasks.getting

gitPublish {
    repoUri.set("https://github.com/britter/bean-validators")
    branch.set("gh-pages")
    sign.set(false)

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
