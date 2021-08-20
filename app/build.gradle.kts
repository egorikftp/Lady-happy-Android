import Modules.Features
import Modules.Libraries
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
    viewBindingEnabled = true
}

android {
    dynamicFeatures += setOf(Modules.DynamicFeatures.edit, Modules.DynamicFeatures.postCreator)

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
    implementation(platform(Libs.firebaseBom))
}

withProjectLibraries(
        Features.catalog,
        Features.detailPage,
        Features.landing,
        Features.launchScreen,
        Features.login,
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

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.balloon,
        Libs.constraintLayout,
        Libs.core,
        Libs.coroutinesAndroid,
        Libs.firebaseAuth,
        Libs.firebaseAnalytics,
        Libs.firebaseCore,
        Libs.firebaseCrashlytics,
        Libs.firebaseFirestore,
        Libs.firebasePerformance,
        Libs.firebaseRemoteConfig,
        Libs.firebaseStorage,
        Libs.fragment,
        Libs.glide,
        Libs.liveDataKtx,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.playCore,
        Libs.sheetsCore,
        Libs.viewBindingDelegates,
        Libs.viewModel
)

dependencies {
    debugImplementation(Libs.beagle)
    debugImplementation(Libs.leakCanary)

    testImplementation(Libs.junit)
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