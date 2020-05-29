package com.egoriku.plugin

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class HappyFeaturePlugin : HappyLibraryPlugin() {

    override fun apply(project: Project) {
        super.apply(project)

        with(project) {
            plugins.all {
                when (this) {
                    is LibraryPlugin -> {
                        extensions.getByType<LibraryExtension>().run {
                            buildFeatures {
                                viewBinding = true
                            }
                        }
                    }
                }
            }
        }
    }
}