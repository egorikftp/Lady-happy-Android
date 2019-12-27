package com.egoriku.ext

import com.egoriku.dependencies.ProjectBean
import org.gradle.api.initialization.Settings
import java.io.File

fun Settings.registerModules(vararg libs: ProjectBean) {
    libs.forEach {
        include(it.name)

        if (it.path.isNotEmpty()) {
            project(it.name).projectDir = File(rootDir, it.path)
        }
    }
}