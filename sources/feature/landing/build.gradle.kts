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
    Libraries.network,
    Libraries.ui
)

dependencies {
    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.coil)
    implementation(libs.coroutines.android)
    implementation(libs.easyadapter)
    implementation(libs.firebase.firestore)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.viewbinding.delegates)
}