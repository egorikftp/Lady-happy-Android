package com.egoriku.ext

import com.android.build.gradle.api.AndroidSourceSet
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.SigningConfig
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<BuildType>.release(setup: BuildType.() -> Unit) =
        get("release", setup)

fun NamedDomainObjectContainer<BuildType>.debug(setup: BuildType.() -> Unit) = get("debug", setup)

fun NamedDomainObjectContainer<AndroidSourceSet>.main(setup: AndroidSourceSet.() -> Unit) =
        get("main", setup)

@JvmName("releaseSigningConfig")
fun NamedDomainObjectContainer<SigningConfig>.release(setup: SigningConfig.() -> Unit) =
        get("release", setup)


@JvmName("buildType")
private fun NamedDomainObjectContainer<BuildType>.get(name: String, block: BuildType.() -> Unit) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}

@JvmName("signingConfig")
private fun NamedDomainObjectContainer<SigningConfig>.get(name: String, block: SigningConfig.() -> Unit) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}

@JvmName("androidSourceSet")
private fun NamedDomainObjectContainer<AndroidSourceSet>.get(name: String, block: AndroidSourceSet.() -> Unit) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}