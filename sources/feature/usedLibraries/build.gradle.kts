import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
    kotlinParcelize = true
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.annotation)
    implementation(libs.constraintlayout)
    implementation(libs.fragment)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.livedata.ktx)
    implementation(libs.recyclerview)
    implementation(libs.viewbinding.delegates)
}

withProjectLibraries(
    Libraries.extensions,
    Libraries.core,
    Libraries.navigation,
    Libraries.network,
    Libraries.ui
)