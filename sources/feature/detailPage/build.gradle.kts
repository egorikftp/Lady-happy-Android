plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBinding = true
}

dependencies {
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.network)
    implementation(projects.sources.base.ui)

    implementation(projects.sources.libraries.extensions)
    implementation(projects.sources.libraries.glideTransformations)
    implementation(projects.sources.libraries.mozaik)

    implementation(libs.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.paging.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.playservices)
    implementation(libs.firebase.firestore)
    implementation(libs.glide)
    implementation(libs.glide.transformation)
    implementation(libs.koin.android)
    implementation(libs.stfalcon.imageviewer)
    implementation(libs.viewbinding.delegates)
}