import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

dependencies {
    implementation(platform(libs.firebase.bom))

    implementation(libs.android.material)
    implementation(libs.appcompat)
    implementation(libs.balloon)
    implementation(libs.cardview)
    implementation(libs.circleImageView)
    implementation(libs.constraintlayout)
    implementation(libs.core)
    implementation(libs.coroutines.android)
    implementation(libs.firebase.firestore)
    implementation(libs.fragment)
    implementation(libs.glide)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.livedata.ktx)
    implementation(libs.recyclerview)
    implementation(libs.viewbinding.delegates)
    implementation(libs.viewmodel)
    implementation(libs.viewpager2)
}

withProjectLibraries(
    Libraries.auth,
    Libraries.core,
    Libraries.extensions,
    Libraries.mozaik,
    Libraries.navigation,
    Libraries.network,
    Libraries.ui
)