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
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.viewbinding.delegates)
}

withProjectLibraries(
    Libraries.extensions,
    Libraries.core,
    Libraries.navigation,
    Libraries.network,
    Libraries.ui
)