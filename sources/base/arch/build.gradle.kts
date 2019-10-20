import com.egoriku.dependencies.Libs
import com.egoriku.dependencies.Modules
import com.egoriku.ext.andKapt
import com.egoriku.ext.withKapt
import com.egoriku.ext.withLibraries
import com.egoriku.ext.withProjects

plugins {
    id("com.egoriku.library")
    id("kotlin-kapt")
}

withProjects(Modules.extensions)

withLibraries(
        Libs.appcompat,
        Libs.material
)

withKapt(Libs.lifecycleExt andKapt Libs.lifecycleCompiler)