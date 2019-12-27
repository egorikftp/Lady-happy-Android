package com.egoriku.ext

import com.android.build.gradle.api.AndroidSourceSet
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.SigningConfig
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
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

fun configureBuildFlavors(
        onLocalBuild: () -> Unit,
        onRemoteBuild: () -> Unit
) = if (System.getenv("IS_APP_CENTER")?.toBoolean() == true) {
    onRemoteBuild.invoke()
} else {
    onLocalBuild()
}