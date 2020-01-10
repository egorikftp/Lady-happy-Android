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
        register("HappyLibraryPlugin") {
            id = "HappyLibraryPlugin"
            implementationClass = "com.egoriku.plugin.HappyLibraryPlugin"
        }

        register("HappyFeaturePlugin") {
            id = "HappyFeaturePlugin"
            implementationClass = "com.egoriku.plugin.HappyFeaturePlugin"
        }
    }
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    implementation("com.android.tools.build:gradle:3.6.0-rc01")
    implementation(kotlin("gradle-plugin", "1.3.61"))
    implementation(kotlin("android-extensions"))
}