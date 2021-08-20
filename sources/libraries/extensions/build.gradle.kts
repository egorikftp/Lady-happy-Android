plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

android {
    lint {
        isAbortOnError = false
    }
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.appcompat)
    implementation(libs.core)
    implementation(libs.exifinterface)
    implementation(libs.fragment)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.recyclerview)
    implementation(libs.viewbinding)
    implementation(libs.viewmodel)
}