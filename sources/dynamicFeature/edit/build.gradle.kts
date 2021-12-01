plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    viewBinding = true
}

dependencies {
    implementation(projects.app)

    implementation(projects.sources.base.auth)
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.localization)
    implementation(projects.sources.base.network)

    implementation(projects.sources.libraries.extensions)
    implementation(projects.sources.libraries.mozaik)

    implementation(platform(libs.firebase.bom))

    implementation(libs.android.material)
    implementation(libs.android.play.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.glide)
    implementation(libs.coroutines.android)
    implementation(libs.firebase.firestore)
    implementation(libs.koin.android)
    implementation(libs.sheets.input)
    implementation(libs.viewbinding.delegates)
}