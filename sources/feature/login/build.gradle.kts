import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyFeaturePlugin")
    id("com.android.library")
}

android {
    lintOptions {
        isAbortOnError = false
    }
}

withProjects(
        Libraries.arch,
        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.koinCore,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.material,
        Libs.viewModelKtx
)