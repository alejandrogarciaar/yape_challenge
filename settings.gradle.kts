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

rootProject.name = "YapeApp"
include(":app")
include(":features:home")
include(":features:detail")
include(":libs:domain")
include(":libs:navigation")
include(":libs:network")
