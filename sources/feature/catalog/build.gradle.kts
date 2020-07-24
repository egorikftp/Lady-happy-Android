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
        Libs.balloon,
        Libs.cardView,
        Libs.circleImageView,
        Libs.constraintLayout,
        Libs.coreKtx,
        Libs.coroutinesAndroid,
        Libs.easyAdapter,
        Libs.firebaseFirestore,
        Libs.fragment,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.liveData,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBindingDelegates,
        Libs.viewModel,
        Libs.viewPager2
)