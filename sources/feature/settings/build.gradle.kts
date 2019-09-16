plugins {
    id("com.egoriku.feature")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":arch"))
    implementation(project(":core"))
    implementation(project(":extensions"))
    implementation(project(":ui"))

    implementation(Libs.browser)
    implementation(Libs.constraintLayout)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.kotlin)

    implementation(Libs.lifecycleExt)
    kapt(Libs.lifecycleCompiler)

    implementation(Libs.material)
}
