plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    compose = true
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.android.material.compose.adapter)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.coil.compose)
}