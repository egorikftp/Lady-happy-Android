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
    lintOptions {
        isAbortOnError = false
    }
}

android {
    configureBuildFlavors(
            onLocalBuild = {
                flavorDimensions("settings")

                productFlavors {
                    full {
                        dimension = "settings"
                    }

                    stub {
                        dimension = "settings"
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
        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.browser,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.circleImageView,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.koinCore,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveData,
        Libs.material,
        Libs.viewBindingDelegates,
        Libs.viewModel
)