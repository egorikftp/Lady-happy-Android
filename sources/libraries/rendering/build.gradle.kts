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

withThirdPartyLibraries(Libs.annotation)