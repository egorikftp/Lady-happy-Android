import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyFeaturePlugin")
    id("com.android.library")
}

withProjects(
        Libraries.easyAdapter,
        Libraries.extensions,
        Libraries.localization
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.material,
        Libs.recyclerView,
        Libs.vectorDrawable
)