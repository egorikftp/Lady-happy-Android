import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyFeaturePlugin")
    id("com.android.library")
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
        Libs.liveData,
        Libs.material,
        Libs.viewBindingDelegates,
        Libs.viewModel
)