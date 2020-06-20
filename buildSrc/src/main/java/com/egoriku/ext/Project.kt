package com.egoriku.ext

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

fun Project.withLibraries(vararg libs: String) {
    dependencies {
        libs.forEach {
            implementation(it)
        }
    }
}

fun Project.withKapt(vararg libs: Pair<String, String>) {
    dependencies {
        libs.forEach {
            implementation(it.first)
            kapt(it.second)
        }
    }
}

fun Project.withProjects(vararg projects: String) {
    dependencies {
        projects.forEach {
            implementation(project(it))
        }
    }
}

val Project.libraryExtension: LibraryExtension
    get() = extensions.getByType()

fun configureBuildFlavors(
        onLocalBuild: () -> Unit,
        onRemoteBuild: () -> Unit
) = if (System.getenv("IS_APP_CENTER")?.toBoolean() == true) {
    onRemoteBuild.invoke()
} else {
    onLocalBuild()
}