import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules.Libraries
import com.egoriku.ext.configureBuildFlavors
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
}

android {
    configureBuildFlavors(
            onLocalBuild = {
                flavorDimensions("settings")

                productFlavors {
                    create("full") {
                        dimension = "settings"
                    }

                    create("stub") {
                        dimension = "settings"
                    }
                }
            },
            onRemoteBuild = {
                sourceSets {
                    with(getByName("main")) {
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
        Libraries.ui
)

withLibraries(
        Libs.browser,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.material
)