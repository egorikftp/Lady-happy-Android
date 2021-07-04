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
        Libraries.localization,
        Libraries.mozaik,
        Libraries.network,
        Libraries.ui
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.cardView,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestore,
        Libs.glide,
        Libs.koinAndroid,
        Libs.lifecycleRuntime,
        Libs.material,
        Libs.recyclerView,
        Libs.stfalconImageViewer,
        Libs.viewBindingDelegates,
        Libs.viewModel
)