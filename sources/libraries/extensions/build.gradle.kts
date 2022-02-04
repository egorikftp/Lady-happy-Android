plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

android {
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.exifinterface)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewbinding)
    implementation(libs.androidx.viewmodel.ktx)
}