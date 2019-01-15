object Versions {
    val kotlin = "1.2.21"
    val support_lib = "26.1.0"
    val retrofit = "2.3.0"
    val rxjava = "2.1.9"
    val min_sdk = 21
    val target_sdk = 26
    val compile_sdk = 26
    val version_code = 1
    val version_name = "1.0"
    val android_gradle_plugin = "3.0.1"
}

object Libs {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.2.21"

    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val app_compat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val support_v4 = "com.android.support:support-v4:${Versions.support_lib}"
    val design = "com.android.support:design:${Versions.support_lib}"
    val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.support_lib}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
}