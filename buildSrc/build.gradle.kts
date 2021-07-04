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

    implementation("com.android.tools.build:gradle:4.2.2")
    implementation(kotlin("gradle-plugin", "1.5.20"))
}