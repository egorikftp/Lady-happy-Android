import com.egoriku.dependencies.Libs
import com.egoriku.ext.withLibraries

plugins {
    id("HappyLibraryPlugin")
    id("com.android.library")
}

withLibraries(
        Libs.appcompat,
        Libs.fragment
)