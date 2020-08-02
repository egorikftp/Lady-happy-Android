import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.github.ben-manes.versions") version "0.29.0"
    id("io.gitlab.arturbosch.detekt") version "1.10.0"
}

buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath(GradlePlugins.firebaseCrashlytics)
        classpath(GradlePlugins.firebasePerformance)
        classpath(GradlePlugins.googleServices)
        classpath(GradlePlugins.googleOssLicenses)
        classpath(GradlePlugins.gradleTools)
        classpath(GradlePlugins.kotlinGradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://artifactory.surfstudio.ru/artifactory/libs-release-local")
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

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "1.8"
    }

    detekt {
        buildUponDefaultConfig = true

        reports {
            html.enabled = true
        }
    }
}

allprojects {
    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.10.0")
    }
}