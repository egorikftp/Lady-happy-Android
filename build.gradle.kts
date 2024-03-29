import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions") version libs.versions.gradleVersionPlugin.get()
    id("io.gitlab.arturbosch.detekt") version libs.versions.gradleDetekt.get()
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.gradle.plugin.buildtools)
        classpath(libs.gradle.plugin.firebase.crashlytics)
        classpath(libs.gradle.plugin.firebase.performance)
        classpath(libs.gradle.plugin.googleservices)
        classpath(libs.gradle.plugin.google.osslicenses)
        classpath(libs.gradle.plugin.kotlin)
    }
}

tasks {
    registering(Delete::class) {
        delete(buildDir)
    }

    named<DependencyUpdatesTask>("dependencyUpdates") {
        resolutionStrategy {
            componentSelection {
                all {
                    val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea", "m1")
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
}

subprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt {
        buildUponDefaultConfig = true
        autoCorrect = true

        reports {
            html.enabled = true
        }
    }
}

allprojects {
    dependencies {
        detektPlugins(rootProject.project.libs.gradle.plugin.detekt.formatting)
    }
}