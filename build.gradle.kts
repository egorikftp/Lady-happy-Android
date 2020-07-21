import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath(GradlePlugins.firebaseCrashlytics)
        classpath(GradlePlugins.firebasePerformance)
        classpath(GradlePlugins.googleServices)
        classpath(GradlePlugins.gradleLibrariesVersion)
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
}

apply(plugin = "com.github.ben-manes.versions")

tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
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