import Modules.DynamicFeatures
import Modules.Features
import Modules.Libraries
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.egoriku.ext.*
import org.gradle.kotlin.dsl.implementation
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.konan.properties.Properties
import org.jetbrains.kotlin.konan.properties.loadProperties
import org.jetbrains.kotlin.konan.properties.saveToFile

plugins {
    id("HappyXPlugin")
    id("com.android.application")
    id("com.google.firebase.firebase-perf")
    id("com.google.firebase.crashlytics")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    dynamicFeatures = mutableSetOf(DynamicFeatures.postCreator)

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

    applicationVariants.all {
        outputs.all {
            if (this is BaseVariantOutputImpl) {
                outputFileName = "${"Lady_Happy"}.${name}_${versionName}.apk"
            }
        }
    }
}

withProjects(
        Features.catalog,
        Features.landing,
        Features.launchScreen,
        Features.login,
        Features.mainScreen,
        Features.photoReport,
        Features.settings,
        Features.usedLibraries,

        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.mozaik,
        Libraries.navigation,
        Libraries.network,
        Libraries.rendering
)

withLibraries(
        Libs.appcompat,
        Libs.core,
        Libs.coroutinesAndroid,
        Libs.firebaseAuth,
        Libs.firebaseCore,
        Libs.firebaseFirestore,
        Libs.firebasePerformance,
        Libs.firebaseRemoteConfig,
        Libs.firebaseStorage,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.playCore
)

dependencies {
    implementation(Libs.firebaseAnalytics)
    implementation(Libs.firebaseCrashlytics)

    debugImplementation(Libs.beagle)
    debugImplementation(Libs.leakCanary)

    testImplementation(Libs.junit)
}

apply {
    plugin("com.google.gms.google-services")
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