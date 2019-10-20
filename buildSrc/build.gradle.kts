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

    implementation("com.android.tools.build:gradle:3.5.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
}