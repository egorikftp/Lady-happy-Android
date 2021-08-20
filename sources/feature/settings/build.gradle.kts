import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

android {
    lint {
        isAbortOnError = false
    }
}

withProjectLibraries(
    Libraries.auth,
    Libraries.core,
    Libraries.extensions,
    Libraries.localization,
    Libraries.navigation,
    Libraries.network,
    Libraries.ui
)

dependencies {
    implementation(libs.android.material)
    implementation(libs.android.play.core.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.livedata.ktx)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.circleImageView)
    implementation(libs.coil)
    implementation(libs.firebase.firestore)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.viewbinding.delegates)
}