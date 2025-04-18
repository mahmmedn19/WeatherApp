pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "WeatherApp"
include(":app")
include(":core")
include(":domain")
include(":data")
include(":weather-utils")
// Feature modules
include(":features:cityinput")
project(":features:cityinput").projectDir = file("features/cityinput")

include(":features:currentweather")
project(":features:currentweather").projectDir = file("features/currentweather")

include(":features:forecast")
project(":features:forecast").projectDir = file("features/forecast")