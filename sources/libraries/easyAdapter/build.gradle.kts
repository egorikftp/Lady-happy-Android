import com.egoriku.ext.withLibraries

plugins {
    id("HappyLibraryPlugin")
    id("com.android.library")
}

withLibraries(
    Libs.annotation,
    Libs.recyclerView
)