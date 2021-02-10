import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjects(
        Libraries.core,
        Libraries.extensions,
        Libraries.network
)

withLibraries(
        Libs.coroutinesAndroid,
        Libs.firebaseAuth
)