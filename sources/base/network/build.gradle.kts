plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core"))

    implementation(Libs.coroutinesAndroid)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.firestore)
    implementation(Libs.kotlin)
}