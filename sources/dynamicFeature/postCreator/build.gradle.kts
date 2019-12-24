import com.egoriku.application.configureProductFlavors
import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.versions.ProjectVersion
import com.egoriku.ext.withLibraries

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
    }

    configureProductFlavors(System.getenv("IS_APP_CENTER")!!.toBoolean())
}

withLibraries(
        Libs.appcompat,
        Libs.playCore
)

dependencies {
    implementation(project(":app"))
}