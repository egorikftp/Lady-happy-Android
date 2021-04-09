import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjectLibraries(Libraries.extensions)

withThirdPartyLibraries(
        Libs.coroutinesAndroid,
        Libs.coroutinesPlayServices,
        Libs.firebaseFirestore,
        Libs.firebaseStorage
)