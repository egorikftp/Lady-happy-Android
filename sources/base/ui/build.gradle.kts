plugins {
    id("com.egoriku.library")
    id("kotlin-android-extensions")
}

dependencies {
    implementation(project(":easyAdapter"))
    implementation(project(":extensions"))

    implementation(Libs.appcompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.kotlin)
    implementation(Libs.material)
    implementation(Libs.recyclerView)
    implementation(Libs.vectorDrawable)
}
