import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.allowExperimentalExtensions
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
    id("kotlin-android-extensions")
}

allowExperimentalExtensions()

withProjects(
        Modules.easyAdapter,
        Modules.extensions
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.kotlin,
        Libs.material,
        Libs.recyclerView,
        Libs.vectorDrawable
)