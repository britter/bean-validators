import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.pmd

plugins {
    pmd
}

pmd {
    sourceSets = listOf(the<SourceSetContainer>()["main"])
}
