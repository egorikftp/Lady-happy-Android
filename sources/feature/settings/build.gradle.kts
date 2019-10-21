import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.feature")
}

withProjects(
        Modules.arch,
        Modules.core,
        Modules.extensions,
        Modules.ui
)

withLibraries(
        Libs.browser,
        Libs.constraintLayout,
        Libs.material
)