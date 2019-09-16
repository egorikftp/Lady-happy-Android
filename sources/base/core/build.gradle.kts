plugins {
    id("com.egoriku.library")
}

dependencies {
    implementation(Libs.cicerone)
    implementation(Libs.coroutinesAndroid)

    implementation(Libs.dagger)
    implementation(Libs.firestore)
    implementation(Libs.kotlin)
}
