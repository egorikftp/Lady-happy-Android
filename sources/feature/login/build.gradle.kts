import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjects(
        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.browser,
        Libs.constraintLayout,
        Libs.core,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.liveData,
        Libs.material,
        Libs.viewBindingDelegates,
        Libs.viewModel
)