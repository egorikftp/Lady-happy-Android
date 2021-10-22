plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.ui)

    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.android)
}