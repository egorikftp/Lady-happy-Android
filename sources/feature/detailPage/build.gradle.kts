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
        Libraries.mozaik,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.glide,
        Libs.glideTransformation,
        Libs.constraintLayout,
        Libs.coroutinesAndroid,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.recyclerView,
        Libs.stfalconImageViewer,
        Libs.viewBindingDelegates,
        Libs.viewModel
)