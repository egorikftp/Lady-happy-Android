import com.egoriku.dependencies.Libs

plugins {
    id("com.egoriku.library")
}

dependencies {
    compileOnly(Libs.appcompat)
    compileOnly(Libs.fragment)
}
