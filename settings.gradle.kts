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

// Home
include(":features:home:home-api")
include(":features:home:home-impl")
include(":features:home:home-wiring-impl")

// Recipe detail
include(":features:recipe-detail:recipe-detail-api")
include(":features:recipe-detail:recipe-detail-impl")
include(":features:recipe-detail:recipe-detail-wiring-impl")

// Libs
include(":libs:domain")
include(":libs:ui-components")
