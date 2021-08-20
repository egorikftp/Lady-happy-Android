import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjectLibraries(
    Libraries.extensions,
    Libraries.localization
)

dependencies {
    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.vectordrawable)
    implementation(libs.easyadapter)
    implementation(libs.viewbinding.delegates)
}