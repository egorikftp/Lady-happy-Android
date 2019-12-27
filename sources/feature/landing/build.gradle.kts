import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.*

plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

android {
    configureBuildFlavors(
            onLocalBuild = {
                flavorDimensions("landing")

                productFlavors {
                    full {
                        dimension = "landing"
                    }

                    stub {
                        dimension = "landing"
                    }
                }
            },
            onRemoteBuild = {
                sourceSets {
                    main {
                        java.srcDirs("src/full/java")
                        res.srcDirs("src/full/res")
                        manifest.srcFile("src/full/AndroidManifest.xml")
                    }
                }
            }
    )
}

withProjects(
        Libraries.arch,
        Libraries.core,
        Libraries.easyAdapter,
        Libraries.extensions,
        Libraries.network,
        Libraries.ui
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
