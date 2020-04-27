import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyLibraryPlugin")
    id("com.android.library")
}

withProjects(
        Libraries.core,
        Libraries.extensions,
        Libraries.network
)

withLibraries(
        Libs.firebaseAuth,
        Libs.liveDataKtx
)