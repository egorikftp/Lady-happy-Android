import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyLibraryPlugin")
    id("com.android.library")
}

withLibraries(
        Libs.glide
)

withProjects(
        Libraries.extensions
)