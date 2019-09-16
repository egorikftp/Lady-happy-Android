plugins {
    id("com.egoriku.library")
}

dependencies {
    implementation(project(":featureProvider"))

    implementation(Libs.appcompat)
}