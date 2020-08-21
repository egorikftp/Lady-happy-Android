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
        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.constraintLayout,
        Libs.core,
        Libs.circleImageView,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.liveData,
        Libs.material,
        Libs.playCore,
        Libs.viewBindingDelegates,
        Libs.viewModel
)