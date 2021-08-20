import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

withProjectLibraries(
    Libraries.core,
    Libraries.ui
)

dependencies {
    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.android)
}