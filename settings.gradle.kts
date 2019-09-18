import com.egoriku.dependencies.SettingsProject

addApp(":app")

addLibraries(
        *SettingsProject.libraries,
        ":core",
        ":featureProvider",
        ":network",
        ":ui"
)

addFeatures(
        ":launchScreen",
        ":mainScreen",
        ":landing",
        ":photoReport",
        ":settings"
)

project(SettingsProject.archProject).projectDir = File(rootDir, "sources/base/arch")
project(SettingsProject.easyAdapter).projectDir = File(rootDir, "sources/libraries/easyAdapter")
project(SettingsProject.extensions).projectDir = File(rootDir, "sources/libraries/extensions")
project(":core").projectDir = File(rootDir, "sources/base/core")
project(":featureProvider").projectDir = File(rootDir, "sources/base/featureProvider")
project(":network").projectDir = File(rootDir, "sources/base/network")
project(":ui").projectDir = File(rootDir, "sources/base/ui")
project(":launchScreen").projectDir = File(rootDir, "sources/feature/launchScreen")
project(":mainScreen").projectDir = File(rootDir, "sources/feature/mainScreen")

project(":landing").projectDir = File(rootDir, "sources/feature/landing")
//project("landing".projectDir = File(rootDir, "sources/feature/landing/stub")

project(":photoReport").projectDir = File(rootDir, "sources/feature/photoReport")
//project("photoReport".projectDir = File(rootDir, "sources/feature/photoReport/stub")

project(":settings").projectDir = File(rootDir, "sources/feature/settings")
//project("settings".projectDir = File(rootDir, "sources/feature/settings/stub")

fun addApp(name: String) {
    include(name)
}

fun addLibraries(vararg libs: String?) {
    include(*libs)
}

fun addFeatures(vararg features: String?) {
    include(*features)
}