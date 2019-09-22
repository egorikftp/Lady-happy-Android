import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.egoriku.dependencies.SettingsProject
import com.egoriku.ext.implementation
import com.egoriku.ext.propertyInt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.konan.properties.Properties
import org.jetbrains.kotlin.konan.properties.loadProperties
import org.jetbrains.kotlin.konan.properties.saveToFile

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.fabric")
}

val properties: Properties = loadProperties("$rootDir/app/version.properties")
val major = properties.propertyInt("VERSION")
val minor = properties.propertyInt("SUB_VERSION")
val patch = properties.propertyInt("BUILD_VERSION")
val versionCodeFromProp = calcVersionCode(major, minor, patch)
val versionNameFromProp = "$major.$minor.$patch"

android {
    buildToolsVersion(ProjectVersion.buildToolsVersion)
    compileSdkVersion(ProjectVersion.compileSdkVersion)

    defaultConfig {
        applicationId = "com.egoriku.ladyhappy"
        minSdkVersion(ProjectVersion.minSdkVersion)
        versionCode = versionCodeFromProp
        versionName = versionNameFromProp
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resConfigs("en", "ru")
    }

    signingConfigs {
        create("release") {
            storeFile = file("lady_happy_key_store.jks")
            storePassword = System.getenv("KEY_STORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            multiDexEnabled = false
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles("proguard-rules.pro", getDefaultProguardFile("proguard-android-optimize.txt"))
        }

        getByName("debug") {
            multiDexEnabled = true
        }
    }

    gradle.taskGraph.whenReady {
        if (hasTask(":app:assembleDebug") || hasTask(":app:assembleRelease")) {
            autoIncrementBuildVersionNumber()
        }
    }

    applicationVariants.all {
        outputs.all {
            if (this is BaseVariantOutputImpl) {
                outputFileName = "${"Lady Happy"}.${name}_${versionName}.apk"
            }
        }
    }
}

withProjects(
        SettingsProject.core,
        SettingsProject.featureProvider,
        SettingsProject.landing,
        SettingsProject.launchScreen,
        SettingsProject.mainScreen,
        SettingsProject.photoReport,
        SettingsProject.settings
)

withLibraries(
        Libs.appcompat,
        Libs.cicerone,
        Libs.firebaseCore,
        Libs.firestore
)

dependencies {
    implementation(kotlin("stdlib-jdk7", Version.kotlin))

    implementation(Libs.crashlytics) {
        isTransitive = true
    }

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    debugImplementation(Libs.leakCanary)

    testImplementation(Libs.junit)
}

apply {
    plugin("com.google.gms.google-services")
}

fun autoIncrementBuildVersionNumber() {
    val properties: Properties = loadProperties("$rootDir/app/version.properties")
    val newVersion = properties.propertyInt("BUILD_VERSION").inc()
    properties["BUILD_VERSION"] = newVersion.toString()

    properties.saveToFile(File("$rootDir/app/version.properties"))
}

fun calcVersionCode(major: Int, minor: Int, patch: Int): Int = major * 100000 + minor * 1000 + patch