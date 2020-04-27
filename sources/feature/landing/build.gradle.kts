import Modules.Libraries
import com.egoriku.ext.*

plugins {
    id("HappyFeaturePlugin")
    id("com.android.library")
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
        Libs.firebaseFirestoreKtx,
        Libs.material,
        Libs.recyclerView,
        Libs.viewModelKtx
)

withKapt(
        Libs.dagger andKapt Libs.daggerCompiler,
        Libs.glide andKapt Libs.glideCompiler
)
