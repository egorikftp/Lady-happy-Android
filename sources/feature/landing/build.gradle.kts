import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjectLibraries(
        Libraries.core,
        Libraries.extensions,
        Libraries.network,
        Libraries.ui
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.coil,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.koinAndroid,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBindingDelegates,
        Libs.viewModel
)