import Modules.Libraries
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
    kotlinParcelize = true
}

withLibraries(
        Libs.annotation,
        Libs.constraintLayout,
        Libs.fragment,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.liveData,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBindingDelegates
)

withProjects(
        Libraries.extensions,
        Libraries.core,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)