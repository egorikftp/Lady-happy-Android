import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions") version ClasspathVersion.versionPlugin
}

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://maven.fabric.io/public")
    }

    dependencies {
        classpath(Plugins.fabric)
        classpath(Plugins.googleServices)
        classpath(Plugins.gradleTools)
        classpath(kotlin("gradle-plugin", version = Version.kotlin))
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks {
    registering(Delete::class) {
        delete(buildDir)
    }
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
    resolutionStrategy {
        componentSelection {
            all {
                val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea")
                        .any { qualifier ->
                            candidate.version.matches(Regex("(?i).*[.-]$qualifier[.\\d-+]*"))
                        }
                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
}