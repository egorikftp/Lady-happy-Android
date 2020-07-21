import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

withProjects(Libraries.extensions)

withLibraries(
        Libs.appcompat,
        Libs.material
)