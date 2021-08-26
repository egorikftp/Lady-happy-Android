import com.egoriku.ext.propertyInt
import com.egoriku.ext.release
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.konan.properties.Properties
import org.jetbrains.kotlin.konan.properties.loadProperties
import org.jetbrains.kotlin.konan.properties.saveToFile

plugins {
    id("HappyXPlugin")
    id("com.android.application")
    id("com.google.firebase.firebase-perf")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.android.gms.oss-licenses-plugin")
}

happyPlugin {
    buildConfigGeneration = true
    compose = true
    viewBinding = true
}

android {
    dynamicFeatures += setOf(
        ":sources:dynamicFeature:adminConsole",
        ":sources:dynamicFeature:edit",
        ":sources:dynamicFeature:postCreator",
    )

    signingConfigs {
        release {
            storeFile = file("lady_happy_key_store.jks")
            storePassword = System.getenv("KEY_STORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            extra["enableCrashlytics"] = true
            extra["alwaysUpdateBuildId"] = true
        }

        debug {
            extra["enableCrashlytics"] = false
            extra["alwaysUpdateBuildId"] = false

            withGroovyBuilder {
                "FirebasePerformance" {
                    invokeMethod("setInstrumentationEnabled", false)
                }
            }
        }
    }

    gradle.taskGraph.whenReady {
        if (hasTask(":app:assembleDebug")) {
            autoIncrementBuildVersionNumber()
        }
    }
}

dependencies {
    implementation(projects.sources.base.auth)
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.composeUi)
    implementation(projects.sources.base.network)

    implementation(projects.sources.feature.catalog)
    implementation(projects.sources.feature.detailPage)
    implementation(projects.sources.feature.landing)
    implementation(projects.sources.feature.launchScreen)
    implementation(projects.sources.feature.login)
    implementation(projects.sources.feature.photoReport)
    implementation(projects.sources.feature.settings)
    implementation(projects.sources.feature.usedLibraries)

    implementation(projects.sources.libraries.datastoreDelegates)
    implementation(projects.sources.libraries.extensions)
    implementation(projects.sources.libraries.navigation)

    implementation(platform(libs.firebase.bom))

    implementation(libs.android.material)
    implementation(libs.android.material.compose.adapter)
    implementation(libs.android.play.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.balloon)
    implementation(libs.coil.compose)
    implementation(libs.coroutines.android)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.core)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.performance)
    implementation(libs.firebase.remoteConfig)
    implementation(libs.firebase.storage)
    implementation(libs.glide)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.sheets.core)
    implementation(libs.viewbinding.delegates)

    debugImplementation(libs.beagle)
    debugImplementation(libs.leakcanary)

    testImplementation(libs.junit)
}

fun autoIncrementBuildVersionNumber() {
    val properties: Properties = loadProperties("$rootDir/app/version.properties")

    val newVersion = properties.propertyInt("BUILD_VERSION").inc()

    if (newVersion == 1000) {
        val subVersion = properties.propertyInt("SUB_VERSION").inc()

        properties["SUB_VERSION"] = subVersion.toString()
        properties["BUILD_VERSION"] = 0.toString()
    } else {
        properties["BUILD_VERSION"] = newVersion.toString()
    }

    properties.saveToFile(File("$rootDir/app/version.properties"))
}