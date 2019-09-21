plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":extensions"))

    implementation(Libs.appcompat)
    implementation(Libs.kotlin)

    kapt(Libs.lifecycleCompiler)
    implementation(Libs.lifecycleExt)
}
