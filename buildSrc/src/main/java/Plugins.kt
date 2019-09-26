import com.egoriku.dependencies.versions.LibrariesVersion

object Plugins {
    const val fabric = "io.fabric.tools:gradle:${ClasspathVersion.fabric}"
    const val googleServices = "com.google.gms:google-services:${ClasspathVersion.googleServices}"
    const val gradleTools = "com.android.tools.build:gradle:${ClasspathVersion.gradleTools}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${LibrariesVersion.kotlin}"
}
