import Modules.Applications
import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    kotlinParcelize = true
    viewBindingEnabled = true
}

android {
    lintOptions {
        isAbortOnError = false
    }
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjectLibraries(
        Applications.ladyHappy,

        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.network,
        Libraries.ui
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.coil,
        Libs.constraintLayout,
        Libs.core,
        Libs.coroutinesAndroid,
        Libs.coroutinesPlayServices,
        Libs.exifInterface,
        Libs.firebaseFirestore,
        Libs.firebaseStorage,
        Libs.fragment,
        Libs.imageCompressor,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.playCore,
        Libs.recyclerView,
        Libs.recyclerViewSelection,
        Libs.viewBindingDelegates,
        Libs.viewModel
)