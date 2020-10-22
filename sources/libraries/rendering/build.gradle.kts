import com.egoriku.ext.withLibraries
import com.egoriku.versions.ProjectVersion

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

android {
    defaultConfig {
        renderscriptTargetApi = ProjectVersion.minSdkVersion
    }
}

withLibraries(Libs.annotation)