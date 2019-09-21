object Version {
    const val annotation = "1.1.0"
    const val appcompat = "1.1.0"
    const val browser = "1.0.0"
    const val cicerone = "5.0.0"
    const val constraintLayout = "2.0.0-beta1"
    const val coroutinesAndroid = "1.3.1"
    const val crashlytics = "2.10.1@aar"
    const val dagger = "2.24"
    const val firebaseCore = "17.2.0"
    const val firestore = "21.1.0"
    const val fragment = "1.1.0"
    const val glide = "4.9.0"
    const val junit = "4.12"
    const val kotlin = "1.3.50"
    const val leakCanary = "2.0-alpha-2"
    const val lifecycle = "2.1.0"
    const val material = "1.0.0"
    const val pageIndicator = "1.0.10"
    const val recyclerView = "1.0.0"
    const val vectorDrawable = "1.1.0"
}

object Libs {
    const val annotation = "androidx.annotation:annotation:${Version.annotation}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val browser = "androidx.browser:browser:${Version.browser}"
    const val cicerone = "ru.terrakok.cicerone:cicerone:${Version.cicerone}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Version.crashlytics}"
    const val dagger = "com.google.dagger:dagger:${Version.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    const val firebaseCore = "com.google.firebase:firebase-core:${Version.firebaseCore}"
    const val firestore = "com.google.firebase:firebase-firestore:${Version.firestore}"
    const val fragment = "androidx.fragment:fragment:${Version.fragment}"
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    const val junit = "junit:junit:${Version.junit}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"
    const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val pageIndicator = "com.ryanjeffreybrooks:indefinitepagerindicator:${Version.pageIndicator}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:${Version.vectorDrawable}"
}

object ClasspathVersion {
    const val fabric = "1.31.0"
    const val googleServices = "4.3.2"
    const val gradleTools = "3.5.0"
    const val versionPlugin = "0.25.0"
}

object Plugins {
    const val fabric = "io.fabric.tools:gradle:${ClasspathVersion.fabric}"
    const val googleServices = "com.google.gms:google-services:${ClasspathVersion.googleServices}"
    const val gradleTools = "com.android.tools.build:gradle:${ClasspathVersion.gradleTools}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
}

object ProjectVersion {
    const val buildToolsVersion = "29.0.2"
    const val compileSdkVersion = 29
    const val minSdkVersion = 21
}