import com.egoriku.application.full
import com.egoriku.application.stub
import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.configureBuildFlavors
import com.egoriku.ext.main
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

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
        Libraries.arch,
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
        Libs.firestore,
        Libs.fragment,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinCore,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.material,
        Libs.recyclerView,
        Libs.viewModel,
        Libs.viewModelKtx,
        Libs.viewPager2
)