package com.egoriku.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.plugins.DynamicFeaturePlugin
import com.egoriku.plugin.extension.MyPluginExtension
import com.egoriku.plugin.internal.appExtension
import com.egoriku.plugin.internal.libraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper

open class HappyXPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.all {
                when (this) {
                    is JavaPlugin -> println("This is JavaPlugin")
                    is LibraryPlugin -> {
                        addCommonPlugins()
                        addAndroidLibrarySection()
                        addCommonDependencies()
                    }
                    is AppPlugin -> {
                        addCommonPlugins()
                        addCommonDependencies()
                        addAndroidApplicationSection()
                    }
                    is DynamicFeaturePlugin -> {
                        addCommonPlugins()
                        addAndroidDynamicSection()
                        addCommonDependencies()
                    }
                    is KotlinBasePluginWrapper -> {
                        tasks.withType(KotlinCompile::class.java).configureEach {
                            kotlinOptions {
                                jvmTarget = "1.8"
                                useIR = true
                            }
                        }
                    }
                }
            }
        }

        val config = project.extensions.create<MyPluginExtension>("happyPlugin")

        project.afterEvaluate {
            plugins.all {
                when (this) {
                    is LibraryPlugin -> {
                        libraryExtension.run {
                            buildFeatures.viewBinding = config.viewBindingEnabled
                        }

                        enableParcelize(config)
                    }

                    is AppPlugin -> {
                        appExtension.run {
                            buildFeatures.viewBinding = config.viewBindingEnabled
                        }
                    }

                    is DynamicFeaturePlugin -> {
                        enableParcelize(config)

                        appExtension.run {
                            buildFeatures.viewBinding = config.viewBindingEnabled
                        }
                    }
                }
            }
        }
    }
}

private fun Project.addAndroidApplicationSection() = appExtension.run {
    defaultConfig {
        applicationId = "com.egoriku.ladyhappy"
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        minSdkVersion(ProjectVersion.minSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
        resConfigs("en", "ru")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        release {
            isDebuggable = false
            multiDexEnabled = false
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro", getDefaultProguardFile("proguard-android-optimize.txt"))
        }

        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            isMinifyEnabled = false
            multiDexEnabled = true
        }
    }
}

private fun Project.addAndroidDynamicSection() = appExtension.run {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = provideVersionCode()
        versionName = provideVersionName()
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        main {
            java.srcDirs("src/main/kotlin")
        }
    }
}

private fun Project.addAndroidLibrarySection() = libraryExtension.run {
    defaultConfig {
        minSdkVersion(ProjectVersion.minSdkVersion)
        compileSdkVersion(ProjectVersion.compileSdkVersion)
        versionCode = 1
        versionName = "1.0"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }

        debug {
            isMinifyEnabled = false
        }
    }

    sourceSets {
        main {
            java.srcDirs("src/main/kotlin")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

private fun Project.addCommonDependencies() = dependencies {
    implementation(Libs.kotlin)
}

private fun Project.enableParcelize(config: MyPluginExtension) {
    if (config.kotlinParcelize) {
        plugins.apply("kotlin-parcelize")
    }
}

private fun Project.addCommonPlugins() {
    plugins.apply("kotlin-android")
}