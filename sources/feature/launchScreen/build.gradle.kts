import Modules.Libraries

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

withProjectLibraries(
        Libraries.core,
        Libraries.ui
)

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.koinAndroid,
        Libs.material
)