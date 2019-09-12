include(":app")

include(":arch")
project(":arch").projectDir = File(rootDir, "sources/base/arch")

include(":core")
project(":core").projectDir = File(rootDir, "sources/base/core")

include(":extensions")
project(":extensions").projectDir = File(rootDir, "sources/libraries/extensions")

include(":featureProvider")
project(":featureProvider").projectDir = File(rootDir, "sources/base/featureProvider")

include(":network")
project(":network").projectDir = File(rootDir, "sources/base/network")

include(":ui")
project(":ui").projectDir = File(rootDir, "sources/base/ui")

include(":easyAdapter")
project(":easyAdapter").projectDir = File(rootDir, "sources/libraries/easyAdapter")

include(":launchScreen")
project(":launchScreen").projectDir = File(rootDir, "sources/feature/launchScreen")

include(":mainScreen")
project(":mainScreen").projectDir = File(rootDir, "sources/feature/mainScreen")

include(":landing")
project(":landing").projectDir = File(rootDir, "sources/feature/landing")
//project("landing".projectDir = File(rootDir, "sources/feature/landing/stub")

include(":photoReport")
project(":photoReport").projectDir = File(rootDir, "sources/feature/photoReport")
//project("photoReport".projectDir = File(rootDir, "sources/feature/photoReport/stub")

include(":settings")
project(":settings").projectDir = File(rootDir, "sources/feature/settings")
//project("settings".projectDir = File(rootDir, "sources/feature/settings/stub")