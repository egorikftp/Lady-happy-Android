import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjects(
        Libraries.core,
        Libraries.extensions,
        Libraries.localization,
        Libraries.mozaik,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.cardView,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestore,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.recyclerView,
        Libs.stfalconImageViewer,
        Libs.viewBindingDelegates,
        Libs.viewModel
)