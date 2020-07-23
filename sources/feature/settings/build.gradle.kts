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
        Libraries.localization,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.browser,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.circleImageView,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.koinCore,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveData,
        Libs.material,
        Libs.viewBindingDelegates,
        Libs.viewModel
)