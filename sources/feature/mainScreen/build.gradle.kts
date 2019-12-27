import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.andKapt
import com.egoriku.ext.withKapt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

withProjects(
        Modules.arch,
        Modules.core,
        Modules.extensions,
        Modules.navigation,
        Modules.ui
)

withLibraries(
        Libs.appcompat,
        Libs.coreKtx,
        Libs.constraintLayout,
        Libs.koinAndroidExt,
        Libs.liveDataKtx,
        Libs.material,
        Libs.playCore
)

withKapt(Libs.dagger andKapt Libs.daggerCompiler)