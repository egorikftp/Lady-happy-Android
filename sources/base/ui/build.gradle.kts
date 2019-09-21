import com.egoriku.dependencies.SettingsProject
import com.egoriku.ext.allowExperimentalExtensions
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
    id("kotlin-android-extensions")
}

allowExperimentalExtensions()

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.kotlin,
        Libs.material,
        Libs.recyclerView,
        Libs.vectorDrawable
)

withProjects(
        SettingsProject.easyAdapter,
        SettingsProject.extensions
)
