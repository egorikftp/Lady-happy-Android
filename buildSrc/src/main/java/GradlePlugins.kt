import com.egoriku.versions.ClasspathVersion
import com.egoriku.versions.LibrariesVersion

object GradlePlugins {
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-gradle:${ClasspathVersion.firebaseCrashlytics}"
    const val firebasePerformance = "com.google.firebase:perf-plugin:${ClasspathVersion.firebasePerformance}"
    const val googleOssLicenses = "com.google.android.gms:oss-licenses-plugin:0.10.2"
    const val googleServices = "com.google.gms:google-services:${ClasspathVersion.googleServices}"
    const val gradleTools = "com.android.tools.build:gradle:${ClasspathVersion.gradleTools}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibrariesVersion.kotlin}"
}