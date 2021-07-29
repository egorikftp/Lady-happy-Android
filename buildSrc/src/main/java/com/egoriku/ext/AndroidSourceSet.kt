package com.egoriku.ext

import com.android.build.api.dsl.AndroidSourceSet
import org.gradle.api.NamedDomainObjectContainer as Container

fun Container<out AndroidSourceSet>.main(setup: AndroidSourceSet.() -> Unit) =
    get("main", setup)

@JvmName("androidSourceSet")
private fun Container<out AndroidSourceSet>.get(
    name: String,
    block: AndroidSourceSet.() -> Unit
) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}