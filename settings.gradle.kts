pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DataViewer"
include(":app")
include(":data")
include(":domain")
include(":presentation")
include(":core:di")
include(":core:ui")
include(":core:navigation")