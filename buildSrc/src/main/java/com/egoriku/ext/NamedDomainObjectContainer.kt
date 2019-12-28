package com.egoriku.ext

import com.android.build.gradle.api.AndroidSourceSet
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.ProductFlavor
import com.android.build.gradle.internal.dsl.SigningConfig
import org.gradle.api.NamedDomainObjectContainer

fun NamedDomainObjectContainer<BuildType>.release(setup: BuildType.() -> Unit) =
        get("release", setup)

fun NamedDomainObjectContainer<BuildType>.debug(setup: BuildType.() -> Unit) = get("debug", setup)

@JvmName("releaseSigningConfig")
fun NamedDomainObjectContainer<SigningConfig>.release(setup: SigningConfig.() -> Unit) =
        get("release", setup)

fun NamedDomainObjectContainer<AndroidSourceSet>.main(setup: AndroidSourceSet.() -> Unit) =
        get("main", setup)

fun NamedDomainObjectContainer<ProductFlavor>.allFeatures(setup: ProductFlavor.() -> Unit) =
        get("allFeatures", setup)

fun NamedDomainObjectContainer<ProductFlavor>.justLanding(setup: ProductFlavor.() -> Unit) =
        get("justLanding", setup)

fun NamedDomainObjectContainer<ProductFlavor>.justPhotoReport(setup: ProductFlavor.() -> Unit) =
        get("justPhotoReport", setup)

fun NamedDomainObjectContainer<ProductFlavor>.justCatalog(setup: ProductFlavor.() -> Unit) =
        get("justCatalog", setup)

fun NamedDomainObjectContainer<ProductFlavor>.justSettings(setup: ProductFlavor.() -> Unit) =
        get("justSettings", setup)

fun NamedDomainObjectContainer<ProductFlavor>.full(setup: ProductFlavor.() -> Unit) =
        get("full", setup)

fun NamedDomainObjectContainer<ProductFlavor>.stub(setup: ProductFlavor.() -> Unit) =
        get("stub", setup)

@JvmName("productFlavor")
private fun NamedDomainObjectContainer<ProductFlavor>.get(name: String, block: ProductFlavor.() -> Unit) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}

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
