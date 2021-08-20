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
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.easyadapter)
    implementation(libs.recyclerview)
    implementation(libs.vectordrawable)
    implementation(libs.viewbinding.delegates)
}