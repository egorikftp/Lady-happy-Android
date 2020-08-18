import Modules.DynamicFeatures
import Modules.Features
import Modules.Libraries
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.ext.*
import com.egoriku.versions.ProjectVersion
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.konan.properties.Properties
import org.jetbrains.kotlin.konan.properties.loadProperties
import org.jetbrains.kotlin.konan.properties.saveToFile

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.firebase.firebase-perf")
    id("com.google.firebase.crashlytics")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    compileSdkVersion(ProjectVersion.compileSdkVersion)

    defaultConfig {
        applicationId = "com.egoriku.ladyhappy"
        minSdkVersion(ProjectVersion.minSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resConfigs("en", "ru")
    }

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
            isDebuggable = false
            multiDexEnabled = false
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles("proguard-rules.pro", getDefaultProguardFile("proguard-android-optimize.txt"))
            extra["enableCrashlytics"] = true
            extra["alwaysUpdateBuildId"] = true
        }

        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isMinifyEnabled = false
            multiDexEnabled = true
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
        Libs.kotlin,
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