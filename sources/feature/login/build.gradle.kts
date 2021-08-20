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
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.viewbinding.delegates)
}