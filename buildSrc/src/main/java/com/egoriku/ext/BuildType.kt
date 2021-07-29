package com.egoriku.ext

import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer as Container

fun Container<BuildType>.release(setup: BuildType.() -> Unit) = get("release", setup)

fun Container<BuildType>.debug(setup: BuildType.() -> Unit) = get("debug", setup)

@JvmName("buildType")
private fun Container<BuildType>.get(name: String, block: BuildType.() -> Unit) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}