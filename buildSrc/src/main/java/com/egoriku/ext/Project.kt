package com.egoriku.ext

import com.android.build.gradle.api.AndroidSourceSet
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.SigningConfig
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
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

fun NamedDomainObjectContainer<BuildType>.release(setup: BuildType.() -> Unit) {
    when (val task = findByName("release")) {
        null -> create("release")
        else -> task
    }.setup()
}

fun NamedDomainObjectContainer<BuildType>.debug(setup: BuildType.() -> Unit) {
    when (val task = findByName("debug")) {
        null -> create("debug")
        else -> task
    }.setup()
}

@JvmName("releaseSigningConfig")
fun NamedDomainObjectContainer<SigningConfig>.release(setup: SigningConfig.() -> Unit) {
    when (val task = findByName("release")) {
        null -> create("release")
        else -> task
    }.setup()
}

fun NamedDomainObjectContainer<AndroidSourceSet>.main(setup: AndroidSourceSet.() -> Unit) {
    when (val task = findByName("main")) {
        null -> create("main")
        else -> task
    }.setup()
}