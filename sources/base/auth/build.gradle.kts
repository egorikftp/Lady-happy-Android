import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

withProjectLibraries(
        Libraries.core,
        Libraries.extensions,
        Libraries.network
)

dependencies {
    implementation(platform(libs.firebase.bom))

    implementation(libs.coroutines.android)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.koin.android)
}