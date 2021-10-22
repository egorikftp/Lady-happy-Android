plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    compose = true
}

dependencies {
    implementation(projects.app)

    implementation(projects.sources.base.composeUi)
    implementation(projects.sources.libraries.extensions)

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.android.material)
    implementation(libs.android.material.compose.adapter)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
}