package com.egoriku.ext

import com.android.build.api.dsl.ApkSigningConfig
import org.gradle.api.NamedDomainObjectContainer as Container

@JvmName("releaseSigningConfig")
fun Container<out ApkSigningConfig>.release(setup: ApkSigningConfig.() -> Unit) =
    get("release", setup)

@JvmName("signingConfig")
private fun Container<out ApkSigningConfig>.get(
    name: String,
    block: ApkSigningConfig.() -> Unit
) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}
