import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.andKapt
import com.egoriku.ext.withKapt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

withProjects(
        Libraries.arch,
        Libraries.core,
        Libraries.extensions,
        Libraries.navigation,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.coreKtx,
        Libs.constraintLayout,
        Libs.koinAndroidExperimental,
        Libs.liveDataKtx,
        Libs.material,
        Libs.playCore
)

withKapt(Libs.dagger andKapt Libs.daggerCompiler)