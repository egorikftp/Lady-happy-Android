import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(platform(libs.firebase.bom))

    implementation(libs.coroutines.android)
    implementation(libs.coroutines.playservices)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
}

withProjectLibraries(Libraries.extensions)