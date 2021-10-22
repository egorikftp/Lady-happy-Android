plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    kotlinParcelize = true
    viewBinding = true
}

dependencies {
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.network)
    implementation(projects.sources.base.ui)

    implementation(projects.sources.libraries.extensions)
    implementation(projects.sources.libraries.navigation)

    implementation(libs.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.koin.android)
    implementation(libs.viewbinding.delegates)
}