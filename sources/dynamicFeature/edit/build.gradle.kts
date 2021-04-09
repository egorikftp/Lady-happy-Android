import Modules.Applications
import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    viewBindingEnabled = true
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjectLibraries(
        Applications.ladyHappy,

        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.mozaik,
        Libraries.network
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.glide,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestore,
        Libs.fragment,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.playCore,
        Libs.sheetsInput,
        Libs.viewBindingDelegates,
        Libs.viewModel
)