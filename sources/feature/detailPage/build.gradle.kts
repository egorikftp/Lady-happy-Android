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
    implementation(libs.constraintlayout)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.playservices)
    implementation(libs.firebase.firestore)
    implementation(libs.fragment)
    implementation(libs.glide)
    implementation(libs.glide.transformation)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.paging)
    implementation(libs.recyclerview)
    implementation(libs.stfalcon.imageviewer)
    implementation(libs.viewbinding.delegates)
    implementation(libs.viewmodel)
}