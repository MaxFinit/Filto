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

rootProject.name = "Filto"
include(":app")
include(":core:ui")
include(":core:network")
include(":core:database")
include(":navigation")
include(":feature:welcome")
include(":feature:feed")

include(":core:common")
include(":core:data")
include(":core:model")
