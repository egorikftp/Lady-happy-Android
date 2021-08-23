plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBinding = true
}

android {
    lint {
        isAbortOnError = false
    }
}

dependencies {
    implementation(projects.sources.base.auth)
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.localization)
    implementation(projects.sources.base.network)
    implementation(projects.sources.base.ui)

    implementation(projects.sources.libraries.extensions)
    implementation(projects.sources.libraries.navigation)

    implementation(libs.android.material)
    implementation(libs.android.play.core.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.circleImageView)
    implementation(libs.coil)
    implementation(libs.firebase.firestore)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.viewbinding.delegates)
}