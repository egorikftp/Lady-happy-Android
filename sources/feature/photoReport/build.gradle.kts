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
    Libraries.localization,
    Libraries.mozaik,
    Libraries.network,
    Libraries.ui
)

dependencies {
    implementation(libs.android.material)
    implementation(libs.appcompat)
    implementation(libs.cardview)
    implementation(libs.constraintlayout)
    implementation(libs.coroutines.android)
    implementation(libs.firebase.firestore)
    implementation(libs.glide)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.recyclerview)
    implementation(libs.stfalcon.imageviewer)
    implementation(libs.viewbinding.delegates)
    implementation(libs.viewmodel)
}