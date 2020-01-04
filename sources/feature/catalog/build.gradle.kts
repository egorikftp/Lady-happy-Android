import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.*

plugins {
    id("com.egoriku.library")
}

android {
    configureBuildFlavors(
            onLocalBuild = {
                flavorDimensions("catalog")

                productFlavors {
                    full {
                        dimension = "catalog"
                    }

                    stub {
                        dimension = "catalog"
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
        Libraries.core,
        Libraries.easyAdapter,
        Libraries.extensions,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.cardView,
        Libs.circleImageView,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestoreKtx,
        Libs.fragment,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinCore,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.material,
        Libs.recyclerView,
        Libs.viewModelKtx,
        Libs.viewPager2
)