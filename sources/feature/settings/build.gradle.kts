import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.*

plugins {
    id("com.egoriku.library")
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
        Libraries.ui
)

withLibraries(
        Libs.browser,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.circleImageView,
        Libs.koinCore,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.material
)