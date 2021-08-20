import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    kotlinParcelize = true
}

dependencies {
    implementation(platform(libs.firebase.bom))

    implementation(libs.android.play.core)
    implementation(libs.appcompat)
    implementation(libs.coroutines.android)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
    implementation(libs.recyclerview)
    implementation(libs.viewmodel)
}

withProjectLibraries(
        Libraries.extensions,
        Libraries.mozaik,
        Libraries.navigation
)