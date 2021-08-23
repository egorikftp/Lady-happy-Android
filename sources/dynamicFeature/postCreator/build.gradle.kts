plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    kotlinParcelize = true
    viewBinding = true
}

android {
    lint {
        isAbortOnError = false
    }
}

dependencies {
    implementation(projects.app)

    implementation(projects.sources.base.auth)
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.localization)
    implementation(projects.sources.base.network)
    implementation(projects.sources.base.ui)

    implementation(projects.sources.libraries.extensions)

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