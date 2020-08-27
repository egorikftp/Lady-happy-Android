import Modules.Applications
import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.dynamic-feature")
}

happyPlugin {
    kotlinParcelize = true
    viewBindingEnabled = true
}

withProjects(
        Applications.ladyHappy,

        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.core,
        Libs.coroutinesAndroid,
        Libs.firebaseStorage,
        Libs.fragment,
        Libs.glide,
        Libs.imageCompressor,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.kotlin,
        Libs.liveData,
        Libs.material,
        Libs.playCore,
        Libs.recyclerView,
        Libs.viewBindingDelegates,
        Libs.viewModel
)

dependencies {
    implementation(Libs.recyclerViewSelection) {
        exclude("recyclerview", "recyclerview")
    }
}