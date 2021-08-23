plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBinding = true
}

dependencies {
    implementation(projects.sources.base.auth)
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.network)
    implementation(projects.sources.base.ui)

    implementation(projects.sources.libraries.extensions)
    implementation(projects.sources.libraries.mozaik)
    implementation(projects.sources.libraries.navigation)

    implementation(platform(libs.firebase.bom))

    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.androidx.viewpager2)
    implementation(libs.balloon)
    implementation(libs.circleImageView)
    implementation(libs.coroutines.android)
    implementation(libs.firebase.firestore)
    implementation(libs.glide)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.viewbinding.delegates)
}