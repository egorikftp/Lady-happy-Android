plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    compose = true
    kotlinParcelize = true
    viewBinding = true
}

android {
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation(projects.app)

    implementation(projects.sources.base.auth)
    implementation(projects.sources.base.core)
    implementation(projects.sources.base.composeUi)
    implementation(projects.sources.base.network)
    implementation(projects.sources.base.ui)

    implementation(projects.sources.libraries.extensions)

    implementation(platform(libs.firebase.bom))

    implementation(libs.android.material)
    implementation(libs.android.material.compose.adapter)
    implementation(libs.android.play.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
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
    implementation(libs.gson)
    implementation(libs.imagecompressor)
    implementation(libs.koin.android)
    implementation(libs.viewbinding.delegates)
}