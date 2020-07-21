import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
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