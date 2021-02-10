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

dependencies {
    implementation(platform(Libs.firebaseBom))
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
        Libs.coil,
        Libs.constraintLayout,
        Libs.core,
        Libs.coroutinesAndroid,
        Libs.coroutinesPlayServices,
        Libs.firebaseFirestore,
        Libs.firebaseStorage,
        Libs.fragment,
        Libs.imageCompressor,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.playCore,
        Libs.recyclerView,
        Libs.recyclerViewSelection,
        Libs.viewBindingDelegates,
        Libs.viewModel
)