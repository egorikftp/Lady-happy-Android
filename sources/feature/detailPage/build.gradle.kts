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
        Libraries.glideTransformations,
        Libraries.mozaik,
        Libraries.network,
        Libraries.ui
)

withThirdPartyLibraries(
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.coroutinesPlayServices,
        Libs.firebaseFirestore,
        Libs.fragment,
        Libs.glide,
        Libs.glideTransformation,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.paging,
        Libs.recyclerView,
        Libs.stfalconImageViewer,
        Libs.viewBindingDelegates,
        Libs.viewModel
)