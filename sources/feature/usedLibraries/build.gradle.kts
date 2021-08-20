import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
    kotlinParcelize = true
}

withThirdPartyLibraries(
        Libs.annotation,
        Libs.constraintLayout,
        Libs.fragment,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBindingDelegates
)

withProjectLibraries(
        Libraries.extensions,
        Libraries.core,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)