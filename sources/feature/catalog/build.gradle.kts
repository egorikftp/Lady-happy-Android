import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.*

plugins {
    id("com.egoriku.feature")
    id("kotlin-android-extensions")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

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

allowExperimentalExtensions()

withProjectsFull(
        Modules.arch,
        Modules.core,
        Modules.easyAdapter,
        Modules.extensions,
        Modules.network,
        Modules.ui
)

forAll(Libs.koinCore)

justForFull(
        Libs.appcompat,
        Libs.cardView,
        Libs.circleImageView,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.coroutinesAndroid,
        Libs.firestore,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.material,
        Libs.recyclerView,
        Libs.viewModel,
        Libs.viewModelKtx,
        Libs.viewPager2
)

justForStub(Libs.fragment)