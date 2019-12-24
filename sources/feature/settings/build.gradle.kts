import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.feature")
}

android {
    if (System.getenv("IS_APP_CENTER")!!.toBoolean()) {
        sourceSets {
            getByName("main").java.srcDirs("src/full/java")
        }
    } else {
        flavorDimensions("settings")

        productFlavors {
            create("full") {
                dimension = "settings"
            }

            create("stub") {
                dimension = "settings"
            }
        }
    }
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