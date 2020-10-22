import Modules.Libraries
import com.egoriku.ext.withProjects

plugins {
    id("HappyXPlugin")
    id("com.android.library")
}

withProjects(Libraries.extensions)