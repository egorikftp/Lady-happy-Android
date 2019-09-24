import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.andKapt
import com.egoriku.ext.withKapt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

withProjects(Modules.core)

withLibraries(
        Libs.coroutinesAndroid,
        Libs.firestore
)

withKapt(Libs.dagger andKapt Libs.daggerCompiler)