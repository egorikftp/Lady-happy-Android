import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.*

plugins {
    id("com.egoriku.feature")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
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
        Libs.koinAndroid,
        Libs.koinCore,
        Libs.koinScope,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.materialBeta,
        Libs.recyclerView,
        Libs.viewModel,
        Libs.viewModelKtx,
        Libs.viewPager2
)

withKapt(
        Libs.dagger andKapt Libs.daggerCompiler,
        Libs.glide andKapt Libs.glideCompiler
)

//TODO move into plugin in Kotlin 1.3.60
android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}