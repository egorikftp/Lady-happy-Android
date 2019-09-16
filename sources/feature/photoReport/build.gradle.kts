plugins {
    id("com.egoriku.feature")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":arch"))
    implementation(project(":core"))
    implementation(project(":easyAdapter"))
    implementation(project(":extensions"))
    implementation(project(":network"))
    implementation(project(":ui"))

    implementation(Libs.appcompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.coroutinesAndroid)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.glide)
    kapt(Libs.glideCompiler)

    implementation(Libs.kotlin)
    implementation(Libs.firestore)
    implementation(Libs.pageIndicator)
    implementation(Libs.recyclerView)
}
