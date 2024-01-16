rootProject.name = "bean-validators"

plugins {
    id("com.gradle.enterprise") version "3.16.1"
    id("com.gradle.common-custom-user-data-gradle-plugin") version "1.12.1"
}

includeBuild("build-logic")

gradleEnterprise {
    buildScan {
        val isCi = !System.getenv("GITHUB_WORKFLOW").isNullOrEmpty()
        if(isCi || file("gradle/.build-scans-tos-agree").exists()) {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            publishAlways()
        }
    }
}
