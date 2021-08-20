plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

gradlePlugin {
    plugins {
        register("HappyXPlugin") {
            id = "HappyXPlugin"
            implementationClass = "com.egoriku.plugin.HappyXPlugin"
        }
    }
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    implementation(libs.gradle.plugin.buildtools)
    implementation(libs.gradle.plugin.kotlin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}