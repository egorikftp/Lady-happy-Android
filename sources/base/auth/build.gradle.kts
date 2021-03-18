import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjectLibraries(
        Libraries.core,
        Libraries.extensions,
        Libraries.network
)

withThirdPartyLibraries(
        Libs.coroutinesAndroid,
        Libs.firebaseAuth,
        Libs.firebaseFirestore,
        Libs.koinAndroid
)