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
    implementation(libs.android.play.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.exifinterface)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.recyclerview.selection)
    implementation(libs.androidx.viewmodel.ktx)
    implementation(libs.coil)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.playservices)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.imagecompressor)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.viewbinding.delegates)
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