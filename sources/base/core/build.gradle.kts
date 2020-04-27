import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyLibraryPlugin")
    id("com.android.library")
}

withProjects(
        Libraries.arch,
        Libraries.navigation
)

withLibraries(
        Libs.coroutinesAndroid,
        Libs.dagger,
        Libs.firebaseFirestoreKtx
)