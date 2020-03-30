import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.versions.ProjectVersion
import com.egoriku.ext.withLibraries

plugins {
    id("HappyLibraryPlugin")
    id("com.android.library")
}

android {
    defaultConfig {
        renderscriptTargetApi = ProjectVersion.minSdkVersion
    }
}

withLibraries(
        Libs.annotation
)