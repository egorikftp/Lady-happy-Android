plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

withThirdPartyLibraries(
        Libs.appcompat,
        Libs.core,
        Libs.fragment,
        Libs.lifecycleRuntime,
        Libs.material,
        Libs.recyclerView,
        Libs.viewBinding,
        Libs.viewModel
)