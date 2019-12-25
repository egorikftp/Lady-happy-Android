plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    implementation("com.android.tools.build:gradle:3.6.0-rc01")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
}