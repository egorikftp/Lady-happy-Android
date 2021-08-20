enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "LadyHappy"

include("app")

include("sources:dynamicFeature:edit")
include("sources:dynamicFeature:postCreator")
include("sources:dynamicFeature:adminConsole")

include("sources:feature:catalog")
include("sources:feature:detailPage")
include("sources:feature:landing")
include("sources:feature:launchScreen")
include("sources:feature:login")
include("sources:feature:photoReport")
include("sources:feature:settings")
include("sources:feature:usedLibraries")

include("sources:base:auth")
include("sources:base:core")
include("sources:base:localization")
include("sources:base:network")
include("sources:base:ui")

include("sources:libraries:extensions")
include("sources:libraries:glideTransformations")
include("sources:libraries:mozaik")
include("sources:libraries:navigation")
