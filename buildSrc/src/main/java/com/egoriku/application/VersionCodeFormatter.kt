package com.egoriku.application

import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.dsl.ProductFlavor
import com.egoriku.ext.propertyInt
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.jetbrains.kotlin.konan.properties.Properties
import org.jetbrains.kotlin.konan.properties.loadProperties

fun Project.provideVersionCode(): Int {
    val properties: Properties = loadProperties("$rootDir/app/version.properties")
    val major = properties.propertyInt("VERSION")
    val minor = properties.propertyInt("SUB_VERSION")
    val patch = properties.propertyInt("BUILD_VERSION")

    return calcVersionCode(major, minor, patch)
}

fun Project.provideVersionName(): String {
    val properties: Properties = loadProperties("$rootDir/app/version.properties")
    val major = properties.propertyInt("VERSION")
    val minor = properties.propertyInt("SUB_VERSION")
    val patch = properties.propertyInt("BUILD_VERSION")

    return "$major.$minor.$patch"
}

private fun calcVersionCode(major: Int, minor: Int, patch: Int): Int = major * 100000 + minor * 1000 + patch

fun AppExtension.configureProductFlavors() {
    flavorDimensions("app")

    productFlavors {
        allFeatures {
            dimension = "app"
            missingDimensionStrategy("catalog", "full")
            missingDimensionStrategy("landing", "full")
            missingDimensionStrategy("photoReport", "full")
            missingDimensionStrategy("settings", "full")
        }

        justLanding {
            dimension = "app"
            missingDimensionStrategy("catalog", "stub")
            missingDimensionStrategy("landing", "full")
            missingDimensionStrategy("photoReport", "stub")
            missingDimensionStrategy("settings", "stub")
        }

        justPhotoReport {
            dimension = "app"
            missingDimensionStrategy("catalog", "stub")
            missingDimensionStrategy("landing", "stub")
            missingDimensionStrategy("photoReport", "full")
            missingDimensionStrategy("settings", "stub")
        }

        justCatalog {
            dimension = "app"
            missingDimensionStrategy("catalog", "full")
            missingDimensionStrategy("landing", "stub")
            missingDimensionStrategy("photoReport", "stub")
            missingDimensionStrategy("settings", "stub")
        }

        justSettings {
            dimension = "app"
            missingDimensionStrategy("catalog", "stub")
            missingDimensionStrategy("landing", "stub")
            missingDimensionStrategy("photoReport", "stub")
            missingDimensionStrategy("settings", "full")
        }
    }
}

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

private fun NamedDomainObjectContainer<ProductFlavor>.get(name: String, block: ProductFlavor.() -> Unit) {
    when (val task = findByName(name)) {
        null -> create(name)
        else -> task
    }.block()
}
