import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.andKapt
import com.egoriku.ext.withKapt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.feature")
    id("kotlin-kapt")
}

android {
    if (System.getenv("IS_APP_CENTER")!!.toBoolean()) {
        sourceSets {
            getByName("main").java.srcDirs("src/full/java")
        }
    } else {
        flavorDimensions("landing")

        productFlavors {
            create("full") {
                dimension = "landing"
            }

            create("stub") {
                dimension = "landing"
            }
        }
    }
}

withProjects(
        Modules.arch,
        Modules.core,
        Modules.easyAdapter,
        Modules.extensions,
        Modules.network,
        Modules.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.firestore,
        Libs.material,
        Libs.recyclerView,
        Libs.viewModel
)

withKapt(
        Libs.dagger andKapt Libs.daggerCompiler,
        Libs.glide andKapt Libs.glideCompiler
)
