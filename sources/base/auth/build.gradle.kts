plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.network)
    implementation(projects.sources.libraries.extensions)

    implementation(platform(libs.firebase.bom))

    implementation(libs.coroutines.android)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.koin.android)
}