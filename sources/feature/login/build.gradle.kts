import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjectLibraries(
    Libraries.auth,
    Libraries.core,
    Libraries.extensions,
    Libraries.navigation,
    Libraries.network,
    Libraries.ui
)

dependencies {
    implementation(libs.android.material)
    implementation(libs.android.playservices.auth)
    implementation(libs.appcompat)
    implementation(libs.browser)
    implementation(libs.constraintlayout)
    implementation(libs.core)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.livedata.ktx)
    implementation(libs.viewbinding.delegates)
    implementation(libs.viewmodel)
}