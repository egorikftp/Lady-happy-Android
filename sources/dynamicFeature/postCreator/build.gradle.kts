import Modules.Applications
import Modules.Libraries
import com.egoriku.application.configureProductFlavors
import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.dependencies.versions.ProjectVersion
import com.egoriku.ext.configureBuildFlavors
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
}

android {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
    }

    configureBuildFlavors(
            onLocalBuild = {
                configureProductFlavors()
            },
            onRemoteBuild = {
                println("It's app center build.")
            }
    )

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    viewBinding {
        isEnabled = true
    }
}

withProjects(
        Applications.ladyHappy,

        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.coroutinesAndroid,
        Libs.imageCompressor,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.kotlin,
        Libs.liveDataKtx,
        Libs.material,
        Libs.playCore,
        Libs.recyclerView,
        Libs.viewModelKtx
)