plugins {
    id("com.egoriku.feature")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":arch"))
    implementation(project(":core"))
    implementation(project(":extensions"))
    implementation(project(":featureProvider"))
    implementation(project(":ui"))

    implementation(Libs.appcompat)
    implementation(Libs.cicerone)
    implementation(Libs.constraintLayout)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    implementation(Libs.kotlin)
    implementation(Libs.material)
}