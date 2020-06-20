import Modules.Libraries
import com.egoriku.ext.*

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
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
        Libraries.extensions,
        Libraries.mozaik,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.balloon,
        Libs.cardView,
        Libs.circleImageView,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.coroutinesAndroid,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.fragment,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinCore,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveData,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBindingDelegates,
        Libs.viewModel,
        Libs.viewPager2
)