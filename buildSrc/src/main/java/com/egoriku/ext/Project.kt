package com.egoriku.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
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

inline fun <reified T : Any> Project.extensionConfig(
        crossinline configure: T.() -> Unit
) {
    project.afterEvaluate {
        val ext: T? = extensions.findByType(T::class)
        ext?.configure()
    }
}

fun configureBuildFlavors(
        onLocalBuild: () -> Unit,
        onRemoteBuild: () -> Unit
) = if (System.getenv("IS_APP_CENTER")?.toBoolean() == true) {
    onRemoteBuild.invoke()
} else {
    onLocalBuild()
}