import com.egoriku.application.configureProductFlavors
import com.egoriku.application.provideVersionCode
import com.egoriku.application.provideVersionName
import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Applications
import com.egoriku.dependencies.versions.ProjectVersion
import com.egoriku.ext.configureBuildFlavors
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
}

android {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
    }

    configureBuildFlavors(
            onLocalBuild = {
                configureProductFlavors()
            },
            onRemoteBuild = {
                println("It's app center build.")
            }
    )

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

withProjects(Applications.ladyHappy)

withLibraries(
        Libs.appcompat,
        Libs.kotlin,
        Libs.playCore
)