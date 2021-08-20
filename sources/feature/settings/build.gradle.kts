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
    implementation(libs.android.play.core)
    implementation(libs.coil)
    implementation(libs.constraintlayout)
    implementation(libs.core)
    implementation(libs.circleImageView)
    implementation(libs.firebase.firestore)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.livedata.ktx)
    implementation(libs.viewbinding.delegates)
    implementation(libs.viewmodel)
}