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
                flavorDimensions("photoReport")

                productFlavors {
                    full {
                        dimension = "photoReport"
                    }

                    stub {
                        dimension = "photoReport"
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
        Libraries.localization,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestoreKtx,
        Libs.pageIndicator,
        Libs.recyclerView
)

withKapt(
        Libs.dagger andKapt Libs.daggerCompiler,
        Libs.glide andKapt Libs.glideCompiler
)
