import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.*

plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

android {
    configureBuildFlavors(
            onLocalBuild = {
                flavorDimensions("photoReport")

                productFlavors {
                    create("full") {
                        dimension = "photoReport"
                    }

                    create("stub") {
                        dimension = "photoReport"
                    }
                }
            },
            onRemoteBuild = {
                sourceSets {
                    getByName("main").java.srcDirs("src/full/java")
                    getByName("main").res.srcDirs("src/full/res")
                    getByName("main").manifest.srcFile("src/full/AndroidManifest.xml")
                }
            }
    )
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
        Libs.pageIndicator,
        Libs.recyclerView
)

withKapt(
        Libs.dagger andKapt Libs.daggerCompiler,
        Libs.glide andKapt Libs.glideCompiler
)
