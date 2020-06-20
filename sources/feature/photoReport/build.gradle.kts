import Modules.Libraries
import com.egoriku.ext.*

plugins {
    id("HappyXPlugin")
    id("com.android.library")
    id("kotlin-kapt")
}

happyPlugin {
    viewBindingEnabled = true
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
        Libraries.extensions,
        Libraries.localization,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.pageIndicator,
        Libs.recyclerView,
        Libs.viewBindingDelegates
)

withKapt(
        Libs.dagger andKapt Libs.daggerCompiler,
        Libs.glide andKapt Libs.glideCompiler
)
