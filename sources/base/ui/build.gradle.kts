import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
}

withProjects(
        Modules.easyAdapter,
        Modules.extensions
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.material,
        Libs.recyclerView,
        Libs.vectorDrawable
)