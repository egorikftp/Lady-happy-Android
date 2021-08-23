plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    compose = true
}

dependencies {
    implementation(projects.app)

    implementation(projects.sources.libraries.extensions)

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.android.material.compose.adapter)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.fragment.ktx)
}