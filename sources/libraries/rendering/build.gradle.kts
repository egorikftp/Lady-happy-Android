import com.egoriku.versions.ProjectVersion
import com.egoriku.ext.withLibraries

plugins {
    id("HappyXPlugin")
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