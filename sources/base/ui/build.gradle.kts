import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyFeaturePlugin")
    id("com.android.library")
}

withProjects(
        Libraries.extensions,
        Libraries.localization
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.easyAdapter,
        Libs.material,
        Libs.recyclerView,
        Libs.vectorDrawable
)