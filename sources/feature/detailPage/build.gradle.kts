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
        Libraries.glideTransformations,
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
        Libs.coroutinesPlayServices,
        Libs.firebaseFirestore,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.material,
        Libs.paging,
        Libs.recyclerView,
        Libs.stfalconImageViewer,
        Libs.viewBindingDelegates,
        Libs.viewModel
)