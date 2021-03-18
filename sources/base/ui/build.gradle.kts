import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

happyPlugin {
    viewBindingEnabled = true
}

withProjectLibraries(
        Libraries.extensions,
        Libraries.localization
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.constraintLayout,
        Libs.easyAdapter,
        Libs.material,
        Libs.recyclerView,
        Libs.vectorDrawable,
        Libs.viewBindingDelegates
)