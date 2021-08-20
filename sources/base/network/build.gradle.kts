plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(projects.sources.libraries.extensions)

    implementation(platform(libs.firebase.bom))

    implementation(libs.coroutines.android)
    implementation(libs.coroutines.playservices)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
}