plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

android {
    lintOptions {
        isAbortOnError = false
    }
}

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.core,
        Libs.exifInterface,
        Libs.fragment,
        Libs.lifecycleRuntime,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBinding,
        Libs.viewModel
)