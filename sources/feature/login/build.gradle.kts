import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjectLibraries(
        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.browser,
        Libs.constraintLayout,
        Libs.core,
        Libs.koinAndroid,
        Libs.lifecycleRuntime,
        Libs.liveDataKtx,
        Libs.material,
        Libs.playServicesAuth,
        Libs.viewBindingDelegates,
        Libs.viewModel
)