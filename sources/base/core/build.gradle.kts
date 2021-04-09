import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    kotlinParcelize = true
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjectLibraries(
        Libraries.extensions,
        Libraries.mozaik,
        Libraries.navigation
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestore,
        Libs.firebaseStorage,
        Libs.playCore,
        Libs.recyclerView,
        Libs.viewModel
)