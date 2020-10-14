plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
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

    implementation("com.android.tools.build:gradle:4.1.0")
    implementation(kotlin("gradle-plugin", "1.4.10"))
    implementation(kotlin("android-extensions", "1.4.10"))
}