plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.core,
        Libs.fragment
)