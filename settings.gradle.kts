rootProject.name = "bean-validators"

plugins {
    id("com.gradle.enterprise") version "3.1.1"
}

gradleEnterprise {
    buildScan {
        val isCi = !System.getenv("GITHUB_WORKFLOW").isNullOrEmpty()
        if(isCi || file("gradle/.build-scans-tos-agree").exists()) {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            publishAlways()
            tag(System.getProperty("os.name"))
            tag(if (isCi) "CI" else "LOCAL")
            val repo = System.getenv("GITHUB_REPOSITORY")
            val runId = System.getenv("GITHUB_RUN_ID")
            if (!repo.isNullOrEmpty() && !runId.isNullOrBlank()) {
                link("GitHub Actions run", "https://github.com/$repo/actions/runs/$runId")
            }
        }
    }
}
