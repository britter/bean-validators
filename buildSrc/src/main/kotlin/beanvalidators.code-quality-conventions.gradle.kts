import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.pmd

plugins {
    pmd
    id("com.github.spotbugs")
}

pmd {
    sourceSets = listOf(the<SourceSetContainer>()["main"])
}

tasks.named("spotbugsTest") {
    enabled = false
}
