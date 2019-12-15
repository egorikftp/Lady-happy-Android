import com.egoriku.dependencies.Libs
import com.egoriku.ext.withLibraries

plugins {
    id("com.egoriku.library")
}

withLibraries(
        Libs.coreKtx,
        Libs.fragment,
        Libs.recyclerView,
        Libs.lifecycleExtensions,
        Libs.viewModel
)
