import Modules.Libraries
import com.egoriku.ext.andKapt
import com.egoriku.ext.withKapt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.android.library")
    id("HappyFeaturePlugin")
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
        Libs.playCore,
        Libs.viewBindingDelegates
)

withKapt(Libs.dagger andKapt Libs.daggerCompiler)