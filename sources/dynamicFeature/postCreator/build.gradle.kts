import Modules.Applications
import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    kotlinParcelize = true
    viewBindingEnabled = true
}

android {
    lint {
        isAbortOnError = false
    }
}

dependencies {
    implementation(platform(libs.firebase.bom))

    implementation(libs.android.material)
    implementation(libs.android.play.core)
    implementation(libs.appcompat)
    implementation(libs.coil)
    implementation(libs.constraintlayout)
    implementation(libs.core)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.playservices)
    implementation(libs.exifinterface)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.fragment)
    implementation(libs.imagecompressor)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.recyclerview)
    implementation(libs.recyclerview.selection)
    implementation(libs.viewbinding.delegates)
    implementation(libs.viewmodel)
}

withProjectLibraries(
    Applications.ladyHappy,

    Libraries.auth,
    Libraries.core,
    Libraries.extensions,
    Libraries.localization,
    Libraries.network,
    Libraries.ui
)