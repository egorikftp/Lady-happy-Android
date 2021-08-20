import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

android {
    lint {
        isAbortOnError = false
    }
}

withProjectLibraries(
        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withThirdPartyLibraries(
        Libs.coil,
        Libs.constraintLayout,
        Libs.core,
        Libs.circleImageView,
        Libs.firebaseFirestore,
        Libs.koinAndroid,
        Libs.liveDataKtx,
        Libs.material,
        Libs.playCore,
        Libs.viewBindingDelegates,
        Libs.viewModel
)