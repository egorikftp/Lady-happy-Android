import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    kotlinParcelize = true
}

withProjects(Libraries.navigation)

withLibraries(
        Libs.appcompat,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestore,
        Libs.firebaseStorage
)