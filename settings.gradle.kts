pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:7.3.0")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.7.10")
            }
        }
    }
}

rootProject.name = "RickAndMortyFacts"
include(":app")
