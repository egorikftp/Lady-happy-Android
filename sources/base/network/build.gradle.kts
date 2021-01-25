import Modules.Libraries
import com.egoriku.ext.implementation
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjects(Libraries.extensions)

withLibraries(
        Libs.coroutinesAndroid,
        Libs.coroutinesPlayServices,
        Libs.firebaseFirestore,
        Libs.firebaseStorage
)