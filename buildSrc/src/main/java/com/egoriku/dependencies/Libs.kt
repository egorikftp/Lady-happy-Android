package com.egoriku.dependencies

import com.egoriku.dependencies.versions.LibrariesVersion

object Libs {
    const val annotation = "androidx.annotation:annotation:${LibrariesVersion.annotation}"
    const val appcompat = "androidx.appcompat:appcompat:${LibrariesVersion.appcompat}"
    const val browser = "androidx.browser:browser:${LibrariesVersion.browser}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${LibrariesVersion.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${LibrariesVersion.coreKtx}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibrariesVersion.coroutinesAndroid}"
    const val crashlytics = "com.crashlytics.sdk.android:crashlytics:${LibrariesVersion.crashlytics}"
    const val dagger = "com.google.dagger:dagger:${LibrariesVersion.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${LibrariesVersion.dagger}"
    const val firebaseCore = "com.google.firebase:firebase-core:${LibrariesVersion.firebaseCore}"
    const val firestore = "com.google.firebase:firebase-firestore:${LibrariesVersion.firestore}"
    const val fragment = "androidx.fragment:fragment:${LibrariesVersion.fragment}"
    const val glide = "com.github.bumptech.glide:glide:${LibrariesVersion.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${LibrariesVersion.glide}"
    const val junit = "junit:junit:${LibrariesVersion.junit}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${LibrariesVersion.kotlin}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${LibrariesVersion.leakCanary}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${LibrariesVersion.lifecycleExtensions}"
    const val material = "com.google.android.material:material:${LibrariesVersion.material}"
    const val pageIndicator = "com.ryanjeffreybrooks:indefinitepagerindicator:${LibrariesVersion.pageIndicator}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${LibrariesVersion.recyclerView}"
    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:${LibrariesVersion.vectorDrawable}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${LibrariesVersion.viewModel}"
}
