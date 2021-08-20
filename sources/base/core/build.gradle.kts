plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    kotlinParcelize = true
}

dependencies {
    implementation(projects.sources.libraries.extensions)
    implementation(projects.sources.libraries.mozaik)
    implementation(projects.sources.libraries.navigation)

    implementation(platform(libs.firebase.bom))

    implementation(libs.android.play.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.coroutines.android)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
}