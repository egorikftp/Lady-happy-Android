import Modules.Applications
import Modules.Libraries
import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects
import com.egoriku.versions.ProjectVersion

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
    }

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
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.core,
        Libs.coroutinesAndroid,
        Libs.firebaseStorage,
        Libs.fragment,
        Libs.glide,
        Libs.imageCompressor,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.kotlin,
        Libs.liveData,
        Libs.material,
        Libs.playCore,
        Libs.recyclerView,
        Libs.viewBindingDelegates,
        Libs.viewModel
)