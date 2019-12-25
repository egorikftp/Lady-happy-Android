import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
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
                    getByName("main").java.srcDirs("src/full/java")
                    getByName("main").res.srcDirs("src/full/res")
                    getByName("main").manifest.srcFile("src/full/AndroidManifest.xml")
                }
            }
    )
}

withProjects(
        Modules.arch,
        Modules.core,
        Modules.extensions,
        Modules.ui
)

withLibraries(
        Libs.browser,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.material
)