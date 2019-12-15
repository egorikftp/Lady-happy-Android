import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.versions.ProjectVersion
import com.egoriku.ext.withLibraries

plugins {
    id("com.egoriku.library")
}

android {
    defaultConfig {
        renderscriptTargetApi = ProjectVersion.minSdkVersion
    }
}

withLibraries(
        Libs.annotation
)