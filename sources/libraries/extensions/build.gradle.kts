import com.egoriku.ext.withLibraries

plugins {
    id("HappyLibraryPlugin")
    id("com.android.library")
}

withLibraries(
        Libs.coreKtx,
        Libs.fragment,
        Libs.recyclerView,
        Libs.lifecycleExtensions,
        Libs.viewModelKtx
)
