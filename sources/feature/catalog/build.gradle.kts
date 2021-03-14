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

dependencies {
    implementation(platform(Libs.firebaseBom))
}

withProjects(
        Libraries.auth,
        Libraries.core,
        Libraries.extensions,
        Libraries.mozaik,
        Libraries.navigation,
        Libraries.network,
        Libraries.ui
)

withLibraries(
        Libs.appcompat,
        Libs.balloon,
        Libs.cardView,
        Libs.circleImageView,
        Libs.constraintLayout,
        Libs.core,
        Libs.coroutinesAndroid,
        Libs.firebaseFirestore,
        Libs.fragment,
        Libs.glide,
        Libs.koinAndroid,
        Libs.koinViewModel,
        Libs.liveDataKtx,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBindingDelegates,
        Libs.viewModel,
        Libs.viewPager2
)