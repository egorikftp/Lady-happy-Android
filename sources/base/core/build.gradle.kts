import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
}

withProjects(
        Libraries.arch,
        Libraries.navigation
)

withLibraries(
        Libs.coroutinesAndroid,
        Libs.dagger,
        Libs.firestore
)