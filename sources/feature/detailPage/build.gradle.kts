import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjectLibraries(
    Libraries.core,
    Libraries.extensions,
    Libraries.glideTransformations,
    Libraries.mozaik,
    Libraries.network,
    Libraries.ui
)

dependencies {
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
    implementation(libs.koin.viewmodel)
    implementation(libs.stfalcon.imageviewer)
    implementation(libs.viewbinding.delegates)
}