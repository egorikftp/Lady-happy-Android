import Modules.Libraries
import com.egoriku.ext.implementation
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    kotlinParcelize = true
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjects(
        Libraries.extensions,
        Libraries.navigation
)

withLibraries(
        Libs.appcompat,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestore,
        Libs.firebaseStorage
)