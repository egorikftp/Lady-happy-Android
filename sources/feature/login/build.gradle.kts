import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
}

withProjects(
        Libraries.arch,
        Libraries.core,
        Libraries.extensions,
        Libraries.navigation,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.koinCore,
        Libs.koinViewModel,
        Libs.material
)