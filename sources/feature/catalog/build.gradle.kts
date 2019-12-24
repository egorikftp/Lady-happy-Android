import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.allowExperimentalExtensions
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.feature")
    id("kotlin-android-extensions")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

    if (System.getenv("IS_APP_CENTER")!!.toBoolean()) {
        sourceSets {
            getByName("main").java.srcDirs("src/full/java")
        }
    } else {
        flavorDimensions("catalog")

        productFlavors {
            create("full") {
                dimension = "catalog"
            }

            create("stub") {
                dimension = "catalog"
            }
        }
    }
}

allowExperimentalExtensions()

withProjects(
        Modules.arch,
        Modules.core,
        Modules.easyAdapter,
        Modules.extensions,
        Modules.network,
        Modules.ui
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