rootProject.name = "bean-validators"

plugins {
    id("com.gradle.enterprise") version "3.1.1"
}

gradleEnterprise {
    buildScan {
        if(!System.getenv("GITHUB_WORKFLOW").isNullOrEmpty() || file("gradle/.build-scans-tos-agree").exists()) {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            publishAlways()
        }
    }
}
