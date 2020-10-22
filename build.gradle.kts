import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.github.ben-manes.versions") version GradlePluginsVersion.versionPlugin
    id("io.gitlab.arturbosch.detekt") version GradlePluginsVersion.detekt
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
        detektPlugins(GradlePlugins.detektFormatting)
    }
}