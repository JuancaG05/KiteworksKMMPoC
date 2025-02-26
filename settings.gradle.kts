enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KiteworksKMMPoC"
include(":androidApp")
include(":shared")
include(":app", ":kiteworks-api-kt")
project(":kiteworks-api-kt").projectDir = File(rootDir, "shared/src/commonMain/kiteworks-api-kt")